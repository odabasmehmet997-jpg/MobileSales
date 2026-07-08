package okhttp3.internal.cache;

import com.fasterxml.jackson.core.JsonFactory;
import com.proje.mobilesales.BuildConfig;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMarkers;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.platform.Platform;
import okio.*;
import java.io.*;
import java.util.*;
import static kotlin.jvm.internal.Intrinsics.*;

public final class DiskLruCache implements Closeable, Flushable {
    public static final Companion Companion = new Companion(null);
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_TEMP = "journal.tmp";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String MAGIC = "libcore.io.DiskLruCache";
    public static final String VERSION_1 = BuildConfig.NETSIS_DEMO_PASSWORD;
    public static final long ANY_SEQUENCE_NUMBER = -1;
    public static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
    public static final String CLEAN = "CLEAN";
    public static final String DIRTY = "DIRTY";
    public static final String REMOVE = "REMOVE";
    public static final String READ = "READ";
    private final int appVersion;
    private final TaskQueue cleanupQueue;
    private final Task cleanupTask;
    private final File directory;
    private final FileSystem fileSystem;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private static LinkedHashMap<String, Entry> lruEntries = null;
    private final int valueCount;
    private final Http2Connection z = null;
    private boolean civilizedFileSystem;
    private boolean closed;
    private boolean hasJournalErrors;
    private boolean initialized;
    private BufferedSink journalWriter;
    private long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    public DiskLruCache(final FileSystem fileSystem, final File directory, final int i2, final int i3, final long j2, final TaskRunner taskRunner) {
        checkNotNullParameter(fileSystem, "fileSystem");
        checkNotNullParameter(directory, "directory");
        checkNotNullParameter(taskRunner, "taskRunner");
        this.fileSystem = fileSystem;
        this.directory = directory;
        appVersion = i2;
        valueCount = i3;
        maxSize = j2;
        lruEntries = new LinkedHashMap<>(0, 0.75f, true);
        cleanupQueue = taskRunner.newQueue();
        String strStringPlus = stringPlus(Util.okHttpName, " Cache");
        cleanupTask = new Task(strStringPlus, z, this) {
            public long runOnce() {
                final DiskLruCache diskLruCache = this.this0;
                synchronized (diskLruCache) {
                    if (!diskLruCache.initialized || diskLruCache.getClosedokhttp()) {
                        return -1L;
                    }
                    try {
                        diskLruCache.trimToSize();
                    } catch (final IOException unused) {
                        diskLruCache.mostRecentTrimFailed = true;
                    }
                    try {
                        if (diskLruCache.journalRebuildRequired()) {
                            diskLruCache.rebuildJournalokhttp();
                            diskLruCache.redundantOpCount = 0;
                        }
                    } catch (final IOException unused2) {
                        diskLruCache.mostRecentRebuildFailed = true;
                        diskLruCache.journalWriter = Okio.buffer(Okio.blackhole());
                    }
                    return -1L;
                }
            }
        };
        if (0 >= j2) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (0 >= i3) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        journalFile = new File(directory, DiskLruCache.JOURNAL_FILE);
        journalFileTmp = new File(directory, DiskLruCache.JOURNAL_FILE_TEMP);
        journalFileBackup = new File(directory, DiskLruCache.JOURNAL_FILE_BACKUP);
    }
    public static Editor editdefault(final DiskLruCache diskLruCache, final String str, long j2, final int i2, final Object obj) throws IOException {
        if (0 != (i2 & 2)) {
            j2 = DiskLruCache.ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache.edit(str, j2);
    }
    public static Editor edit(DiskLruCache cache, String key, long l, int i, Object o) {
        return null;
    }
    public Editor edit(final String key) throws IOException {
        checkNotNullParameter(key, "key");
        return DiskLruCache.editdefault(this, key, 0L, 2, null);
    }
    public FileSystem getFileSystemokhttp() {
        return fileSystem;
    }
    public File getDirectory() {
        return directory;
    }
    public int getValueCountokhttp() {
        return valueCount;
    }
    public synchronized long getMaxSize() {
        return maxSize;
    }
    public synchronized void setMaxSize(final long j2) {
        maxSize = j2;
        if (initialized) {
            TaskQueue.scheduledefault(cleanupQueue, cleanupTask, 0L, 2, null);
        }
    }
    public static LinkedHashMap<String, Entry> getLruEntriesokhttp() {
        return lruEntries;
    }
    public boolean getClosedokhttp() {
        return closed;
    }
    public void setClosedokhttp(final boolean z) {
        closed = z;
    }
    private void readJournal() throws IOException {
        final BufferedSource bufferedSourceBuffer = Okio.buffer(fileSystem.source(journalFile)).getBuffer();
        String utf8LineStrict = bufferedSourceBuffer.readUtf8LineStrict();
        String utf8LineStrict2 = bufferedSourceBuffer.readUtf8LineStrict();
        String utf8LineStrict3 = bufferedSourceBuffer.readUtf8LineStrict();
        String utf8LineStrict4 = bufferedSourceBuffer.readUtf8LineStrict();
        String utf8LineStrict5 = bufferedSourceBuffer.readUtf8LineStrict();
        if (!areEqual(MAGIC, utf8LineStrict) || !areEqual(VERSION_1, utf8LineStrict2) || !areEqual(String.valueOf(this.appVersion), utf8LineStrict3) || !areEqual(String.valueOf(valueCount), utf8LineStrict4) || 0 < utf8LineStrict5.length()) {
            throw new IOException("unexpected journal header: [" + utf8LineStrict + ", " + utf8LineStrict2 + ", " + utf8LineStrict4 + ", " + utf8LineStrict5 + ']');
        }
        int i2 = 0;
        while (true) {
            try {
                readJournalLine(bufferedSourceBuffer.readUtf8LineStrict());
                i2++;
            } catch (EOFException unused) {
                this.redundantOpCount = i2 - lruEntries.size();
                if (!bufferedSourceBuffer.exhausted()) {
                    rebuildJournalokhttp();
                } else {
                    this.journalWriter = newJournalWriter();
                }
                Unit unit = Unit.INSTANCE;
                kotlin.io.Closeable.closeFinally(bufferedSourceBuffer, null);
                return;
            }
        }
    }
    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(fileSystem.appendingSink(journalFile), new Function1<IOException, Unit>() {
            private DiskLruCache this0;
            public Unit invoke(final Object iOException) {
                this.invoke2((IOException) iOException);
                return Unit.INSTANCE;
            } 
            public void detachokhttp() {
                
            }

            public void invoke2(final IOException it) {
                checkNotNullParameter(it, "it");
                final DiskLruCache diskLruCache = this.this0;
                if (!Util.assertionsEnabled || Thread.holdsLock(diskLruCache)) {
                    this.this0.hasJournalErrors = true;
                    return;
                }
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + diskLruCache);
            }
        }));
    }
    private void readJournalLine(final String str) throws IOException {
        final String strSubstring;
        final int iIndexOfdefault = StringsKt.indexOf(str, ' ', 0, false);
        if (-1 == iIndexOfdefault) {
            throw new IOException(stringPlus("unexpected journal line: ", str));
        }
        final int i2 = iIndexOfdefault + 1;
        final int iIndexOfdefault2 = StringsKt.indexOf(str, ' ', i2, false);
        if (-1 == iIndexOfdefault2) {
            strSubstring = str.substring(i2);
            checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
            final String str2 = DiskLruCache.REMOVE;
            if (iIndexOfdefault == str2.length() && StringsKt.startsWith(str, str2, false)) {
                lruEntries.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i2, iIndexOfdefault2);
            checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
        }
        Entry entry = lruEntries.get(strSubstring);
        if (null == entry) {
            entry = new Entry(this, strSubstring);
            lruEntries.put(strSubstring, entry);
        }
        if (-1 != iIndexOfdefault2) {
            final String str3 = DiskLruCache.CLEAN;
            if (iIndexOfdefault == str3.length() && StringsKt.startsWith(str, str3, false)) {
                final String strSubstring2 = str.substring(iIndexOfdefault2 + 1);
                checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
                final List<String> listSplitdefault = StringsKt.split(strSubstring2, new char[]{' '}, false, 0);
                entry.setReadableokhttp(true);
                entry.setCurrentEditorokhttp(null);
                entry.setLengthsokhttp(listSplitdefault);
                return;
            }
        }
        if (-1 == iIndexOfdefault2) {
            final String str4 = DiskLruCache.DIRTY;
            if (iIndexOfdefault == str4.length() && StringsKt.startsWith(str, str4, false)) {
                entry.setCurrentEditorokhttp(new Editor(this, entry));
                return;
            }
        }
        if (-1 == iIndexOfdefault2) {
            final String str5 = DiskLruCache.READ;
            if (iIndexOfdefault == str5.length() && StringsKt.startsWith(str, str5, false)) {
                return;
            }
        }
        throw new IOException(stringPlus("unexpected journal line: ", str));
    }
    private void processJournal() throws IOException {
        fileSystem.delete(journalFileTmp);
        final Iterator<Entry> it = lruEntries.values().iterator();
        while (it.hasNext()) {
            final Entry next = it.next();
            checkNotNullExpressionValue(next, "i.next()");
            final Entry entry = next;
            int i2 = 0;
            if (null == entry.getCurrentEditorokhttp()) {
                final int i3 = valueCount;
                while (i2 < i3) {
                    size += entry.getLengthsokhttp()[i2];
                    i2++;
                }
            } else {
                entry.setCurrentEditorokhttp(null);
                final int i4 = valueCount;
                while (i2 < i4) {
                    fileSystem.delete(entry.getCleanFilesokhttp().get(i2));
                    fileSystem.delete(entry.getDirtyFilesokhttp().get(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }
    public synchronized void rebuildJournalokhttp() throws IOException {
        try {
            final BufferedSink bufferedSink = journalWriter;
            if (null != bufferedSink) {
                bufferedSink.close();
            }
            final BufferedSink bufferedSinkBuffer = Okio.buffer(fileSystem.sink(journalFileTmp));
            bufferedSinkBuffer.writeUtf8(MAGIC).writeByte(10);
            bufferedSinkBuffer.writeUtf8(VERSION_1).writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(this.appVersion).writeByte(10);
            bufferedSinkBuffer.writeDecimalLong(valueCount).writeByte(10);
            bufferedSinkBuffer.writeByte(10);
            for (Entry entry : lruEntries.values()) {
                if (null != entry.getCurrentEditorokhttp()) {
                    bufferedSinkBuffer.writeUtf8(DIRTY).writeByte(32);
                    bufferedSinkBuffer.writeUtf8(entry.getKeyokhttp());
                    bufferedSinkBuffer.writeByte(10);
                } else {
                    bufferedSinkBuffer.writeUtf8(CLEAN).writeByte(32);
                    bufferedSinkBuffer.writeUtf8(entry.getKeyokhttp());
                    entry.writeLengthsokhttp(bufferedSinkBuffer);
                    bufferedSinkBuffer.writeByte(10);
                }
            }
            Unit unit = Unit.INSTANCE;
            kotlin.io.Closeable.closeFinally((Source) bufferedSinkBuffer, null);
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.rename(this.journalFile, this.journalFileBackup);
            }
            this.fileSystem.rename(this.journalFileTmp, this.journalFile);
            this.fileSystem.delete(this.journalFileBackup);
            this.journalWriter = newJournalWriter();
            this.hasJournalErrors = false;
            this.mostRecentRebuildFailed = false;
        } catch (final Throwable th) {
            throw th;
        }
    }
    public synchronized Snapshot get(final String key) throws IOException {
        checkNotNullParameter(key, "key");
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        final Entry entry = lruEntries.get(key);
        if (null == entry) {
            return null;
        }
        final Snapshot snapshotSnapshotokhttp = entry.snapshotokhttp();
        if (null == snapshotSnapshotokhttp) {
            return null;
        }
        redundantOpCount++;
        final BufferedSink bufferedSink = journalWriter;
        checkNotNull(bufferedSink);
        bufferedSink.writeUtf8(DiskLruCache.READ).writeByte(32).writeUtf8(key).writeByte(10);
        if (this.journalRebuildRequired()) {
            TaskQueue.scheduledefault(cleanupQueue, cleanupTask, 0L, 2, null);
        }
        return snapshotSnapshotokhttp;
    }
    public synchronized Editor edit(final String key, final long j2) throws IOException {
        checkNotNullParameter(key, "key");
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        Entry entry = lruEntries.get(key);
        if (DiskLruCache.ANY_SEQUENCE_NUMBER != j2 && (null == entry || entry.getSequenceNumberokhttp() != j2)) {
            return null;
        }
        if (null != (null == entry ? null : entry.getCurrentEditorokhttp())) {
            return null;
        }
        if (null != entry && 0 != entry.getLockingSourceCountokhttp()) {
            return null;
        }
        if (!mostRecentTrimFailed && !mostRecentRebuildFailed) {
            final BufferedSink bufferedSink = journalWriter;
            checkNotNull(bufferedSink);
            bufferedSink.writeUtf8(DiskLruCache.DIRTY).writeByte(32).writeUtf8(key).writeByte(10);
            bufferedSink.flush();
            if (hasJournalErrors) {
                return null;
            }
            if (null == entry) {
                entry = new Entry(this, key);
                lruEntries.put(key, entry);
            }
            final Editor editor = new Editor(this, entry);
            entry.setCurrentEditorokhttp(editor);
            return editor;
        }
        TaskQueue.scheduledefault(cleanupQueue, cleanupTask, 0L, 2, null);
        return null;
    }
    public synchronized long size() throws IOException {
        this.initialize();
        return size;
    }
    public synchronized void completeEditokhttp(final Editor editor, final boolean z) throws IOException {
        checkNotNullParameter(editor, "editor");
        final Entry entryokhttp = editor.getEntryokhttp();
        if (!areEqual(entryokhttp.getCurrentEditorokhttp(), editor)) {
            throw new IllegalStateException("Check failed.");
        }
        int i2 = 0;
        if (z && !entryokhttp.getReadableokhttp()) {
            final int i3 = valueCount;
            int i4 = 0;
            while (i4 < i3) {
                final int i5 = i4 + 1;
                final boolean[] writtenokhttp = editor.getWrittenokhttp();
                checkNotNull(writtenokhttp);
                if (!writtenokhttp[i4]) {
                    editor.abort();
                    throw new IllegalStateException(stringPlus("Newly created entry didn't create value for index ", Integer.valueOf(i4)));
                }
                if (!fileSystem.exists(entryokhttp.getDirtyFilesokhttp().get(i4))) {
                    editor.abort();
                    return;
                }
                i4 = i5;
            }
        }
        final int i6 = valueCount;
        while (i2 < i6) {
            final int i7 = i2 + 1;
            final File file = entryokhttp.getDirtyFilesokhttp().get(i2);
            if (z && !entryokhttp.getZombieokhttp()) {
                if (fileSystem.exists(file)) {
                    final File file2 = entryokhttp.getCleanFilesokhttp().get(i2);
                    fileSystem.rename(file, file2);
                    final long j2 = entryokhttp.getLengthsokhttp()[i2];
                    final long size = fileSystem.size(file2);
                    entryokhttp.getLengthsokhttp()[i2] = size;
                    this.size = (this.size - j2) + size;
                }
            } else {
                fileSystem.delete(file);
            }
            i2 = i7;
        }
        entryokhttp.setCurrentEditorokhttp(null);
        if (entryokhttp.getZombieokhttp()) {
            this.removeEntryokhttp();
            return;
        }
        redundantOpCount++;
        final BufferedSink bufferedSink = journalWriter;
        checkNotNull(bufferedSink);
        if (entryokhttp.getReadableokhttp() || z) {
            entryokhttp.setReadableokhttp(true);
            bufferedSink.writeUtf8(DiskLruCache.CLEAN).writeByte(32);
            bufferedSink.writeUtf8(entryokhttp.getKeyokhttp());
            entryokhttp.writeLengthsokhttp(bufferedSink);
            bufferedSink.writeByte(10);
            if (z) {
                final long j3 = nextSequenceNumber;
                nextSequenceNumber = 1 + j3;
                entryokhttp.setSequenceNumberokhttp(j3);
            }
        } else {
            lruEntries.remove(entryokhttp.getKeyokhttp());
            bufferedSink.writeUtf8(DiskLruCache.REMOVE).writeByte(32);
            bufferedSink.writeUtf8(entryokhttp.getKeyokhttp());
            bufferedSink.writeByte(10);
        }
        bufferedSink.flush();
        if (size > maxSize || this.journalRebuildRequired()) {
            TaskQueue.scheduledefault(cleanupQueue, cleanupTask, 0L, 2, null);
        }
    }
    private boolean journalRebuildRequired() {
        final int i2 = redundantOpCount;
        return 2000 <= i2 && i2 >= lruEntries.size();
    }
    public synchronized boolean remove(final String key) throws IOException {
        checkNotNullParameter(key, "key");
        this.initialize();
        this.checkNotClosed();
        this.validateKey(key);
        final Entry entry = lruEntries.get(key);
        if (null == entry) {
            return false;
        }
        final boolean zRemoveEntryokhttp = this.removeEntryokhttp();
        if (zRemoveEntryokhttp && size <= maxSize) {
            mostRecentTrimFailed = false;
        }
        return zRemoveEntryokhttp;
    }
    public boolean removeEntryokhttp() throws IOException {
        final BufferedSink bufferedSink;
        Entry entry = null;
        checkNotNullParameter(entry, "entry");
        if (!civilizedFileSystem) {
            if (0 < entry.getLockingSourceCountokhttp() && null != (bufferedSink = this.journalWriter)) {
                bufferedSink.writeUtf8(DiskLruCache.DIRTY);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(entry.getKeyokhttp());
                bufferedSink.writeByte(10);
                bufferedSink.flush();
            }
            if (0 < entry.getLockingSourceCountokhttp() || null != entry.getCurrentEditorokhttp()) {
                entry.setZombieokhttp(true);
                return true;
            }
        }
        final Editor currentEditorokhttp = entry.getCurrentEditorokhttp();
        if (null != currentEditorokhttp) {
            currentEditorokhttp.detachokhttp();
        }
        final int i2 = valueCount;
        for (int i3 = 0; i3 < i2; i3++) {
            fileSystem.delete(entry.getCleanFilesokhttp().get(i3));
            size -= entry.getLengthsokhttp()[i3];
            entry.getLengthsokhttp()[i3] = 0;
        }
        redundantOpCount++;
        final BufferedSink bufferedSink2 = journalWriter;
        if (null != bufferedSink2) {
            bufferedSink2.writeUtf8(DiskLruCache.REMOVE);
            bufferedSink2.writeByte(32);
            bufferedSink2.writeUtf8(entry.getKeyokhttp());
            bufferedSink2.writeByte(10);
        }
        lruEntries.remove(entry.getKeyokhttp());
        if (this.journalRebuildRequired()) {
            TaskQueue.scheduledefault(cleanupQueue, cleanupTask, 0L, 2, null);
        }
        return true;
    }
    public synchronized void initialize() throws IOException {
        try {
            if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
            }
            if (initialized) {
                return;
            }
            if (fileSystem.exists(journalFileBackup)) {
                if (fileSystem.exists(journalFile)) {
                    fileSystem.delete(journalFileBackup);
                } else {
                    fileSystem.rename(journalFileBackup, journalFile);
                }
            }
            civilizedFileSystem = Util.isCivilized((okhttp3.internal.io.FileSystem) fileSystem, journalFileBackup);
            if (fileSystem.exists(journalFile)) {
                try {
                    this.readJournal();
                    this.processJournal();
                    initialized = true;
                    return;
                } catch (final IOException e2) {
                    Platform.Companion.get().log("DiskLruCache " + directory + " is corrupt: " + e2.getMessage() + ", removing", 5, e2);
                    try {
                        this.delete();
                        closed = false;
                    } catch (final Throwable th) {
                        closed = false;
                        throw th;
                    }
                }
            }
            this.rebuildJournalokhttp();
            initialized = true;
        } catch (final Throwable th2) {
            throw th2;
        }
    }
    private synchronized void checkNotClosed() {
        if (closed) {
            throw new IllegalStateException("cache is closed");
        }
    } 
    public synchronized void flush() throws IOException {
        if (initialized) {
            this.checkNotClosed();
            this.trimToSize();
            final BufferedSink bufferedSink = journalWriter;
            checkNotNull(bufferedSink);
            bufferedSink.flush();
        }
    }
    public synchronized boolean isClosed() {
        return closed;
    } 
    public synchronized void close() throws IOException {
        Editor currentEditorokhttp;
        try {
            if (initialized && !closed) {
                final Collection<Entry> collectionValues = lruEntries.values();
                checkNotNullExpressionValue(collectionValues, "lruEntries.values");
                int i2 = 0;
                final Object[] array = collectionValues.toArray(new Entry[0]);
                if (null == array) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                final Entry[] entryArr = (Entry[]) array;
                final int length = entryArr.length;
                while (i2 < length) {
                    final Entry entry = entryArr[i2];
                    i2++;
                    if (null != entry.getCurrentEditorokhttp() && null != (currentEditorokhttp = entry.getCurrentEditorokhttp())) {
                        currentEditorokhttp.detachokhttp();
                    }
                }
                this.trimToSize();
                final BufferedSink bufferedSink = journalWriter;
                checkNotNull(bufferedSink);
                bufferedSink.close();
                journalWriter = null;
                closed = true;
                return;
            }
            closed = true;
        } catch (final Throwable th) {
            throw th;
        }
    }
    public void trimToSize() throws IOException {
        while (size > maxSize) {
            if (!this.removeOldestEntry()) {
                return;
            }
        }
        mostRecentTrimFailed = false;
    }
    private boolean removeOldestEntry() throws IOException {
        for (final Entry toEvict : lruEntries.values()) {
            if (!toEvict.getZombieokhttp()) {
                checkNotNullExpressionValue(toEvict, "toEvict");
                this.removeEntryokhttp();
                return true;
            }
        }
        return false;
    }
    public void delete() throws IOException {
        this.close();
        fileSystem.deleteContents(directory);
    }
    public synchronized void evictAll() throws IOException {
        try {
            this.initialize();
            final Collection<Entry> collectionValues = lruEntries.values();
            checkNotNullExpressionValue(collectionValues, "lruEntries.values");
            final Entry[] entryArr = collectionValues.toArray(new Entry[0]);
            final int length = entryArr.length;
            int i2 = 0;
            while (i2 < length) {
                final Entry entry = entryArr[i2];
                i2++;
                checkNotNullExpressionValue(entry, "entry");
                this.removeEntryokhttp();
            }
            mostRecentTrimFailed = false;
        } catch (final Throwable th) {
            throw th;
        }
    }
    private void validateKey(final String str) {
        if (DiskLruCache.LEGAL_KEY_PATTERN.matches(str)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + JsonFactory.DEFAULT_QUOTE_CHAR));
    }
    public synchronized Iterator<Snapshot> snapshots() throws IOException {
        this.initialize();
        return new C35851();
    }
    public long cleanup(long l) {
        return l;
    }
    public final class C35851 implements Iterator<Snapshot>, KMarkers {
        private final Iterator<Entry> delegate;
        private Snapshot nextSnapshot;
        private Snapshot removeSnapshot;
        C35851() {
            final Iterator<Entry> it = new ArrayList(getLruEntriesokhttp().values()).iterator();
            checkNotNullExpressionValue(it, "ArrayList(lruEntries.values).iterator()");
            delegate = it;
        } 
        public boolean hasNext() {
            if (null != this.nextSnapshot) {
                return true;
            }
            final DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (diskLruCache.getClosedokhttp()) {
                    return false;
                }
                while (delegate.hasNext()) {
                    final Entry next = delegate.next();
                    final Snapshot snapshotSnapshotokhttp = null == next ? null : next.snapshotokhttp();
                    if (null != snapshotSnapshotokhttp) {
                        nextSnapshot = snapshotSnapshotokhttp;
                        return true;
                    }
                }
                final Unit unit = Unit.INSTANCE;
                return false;
            }
        } 
        public Snapshot next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Snapshot snapshot = nextSnapshot;
            removeSnapshot = snapshot;
            nextSnapshot = null;
            checkNotNull(snapshot);
            return snapshot;
        } 
        public void remove() {
            final Snapshot snapshot = removeSnapshot;
            if (null == snapshot) {
                throw new IllegalStateException("remove() before next()");
            }
            try {
                DiskLruCache.this.remove(snapshot.key());
            } catch (final IOException unused) {
            } catch (final Throwable th) {
                removeSnapshot = null;
                throw th;
            }
            removeSnapshot = null;
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
    public final class Snapshot implements Closeable {
        final DiskLruCache this0;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final List<Source> sources;
        public Snapshot(final DiskLruCache this0, final String key, final long j2, final List<? extends Source> sources, final long[] lengths) {
            checkNotNullParameter(this0, "this0");
            checkNotNullParameter(key, "key");
            checkNotNullParameter(sources, "sources");
            checkNotNullParameter(lengths, "lengths");
            this.this0 = this0;
            this.key = key;
            sequenceNumber = j2;
            this.sources = (List<Source>) sources;
            this.lengths = lengths;
        }

        public String key() {
            return key;
        }

        public Editor edit() throws IOException {
            return this0.edit(key, sequenceNumber);
        }

        public Source getSource(final int i2) {
            return sources.get(i2);
        }

        public long getLength(final int i2) {
            return lengths[i2];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            final Iterator<Source> it = sources.iterator();
            while (it.hasNext()) {
                Util.closeQuietly(it.next());
            }
        }
    }
    public static final class Editor {
        final DiskLruCache this0;
        private final Entry entry;
        private final boolean[] written;
        private boolean done;
        public Editor(final DiskLruCache this0, final Entry entry) {
            checkNotNullParameter(this0, "this0");
            checkNotNullParameter(entry, "entry");
            this.this0 = this0;
            this.entry = entry;
            written = entry.getReadableokhttp() ? null : new boolean[this0.getValueCountokhttp()];
        }

        public Editor(Editor editorEdit, DiskLruCache this0, Entry entry, boolean[] written) {
            this.this0 = this0;
            this.entry = entry;
            this.written = written;
        }

        public Entry getEntryokhttp() {
            return entry;
        }
        public boolean[] getWrittenokhttp() {
            return written;
        }
        public void detachokhttp() throws IOException {
            if (areEqual(entry.getCurrentEditorokhttp(), this)) {
                if (this0.civilizedFileSystem) {
                    this0.completeEditokhttp(this, false);
                } else {
                    entry.setZombieokhttp(true);
                }
            }
        }
        public Source newSource(final int i2) {
            final DiskLruCache diskLruCache = this0;
            synchronized (diskLruCache) {
                if (done) {
                    throw new IllegalStateException("Check failed.");
                }
                Source source = null;
                if (!this.entry.getReadableokhttp() || !areEqual(this.entry.getCurrentEditorokhttp(), this) || this.entry.getZombieokhttp()) {
                    return null;
                }
                source = (Source) diskLruCache.getFileSystemokhttp().source(this.entry.getCleanFilesokhttp().get(i2));
                return source;
            }
        }
        public Sink newSink(final int i2) throws FileNotFoundException {
            DiskLruCache diskLruCache = this0;
            synchronized (diskLruCache) {
                if (done) {
                    throw new IllegalStateException("Check failed.");
                }
                if (!areEqual(this.entry.getCurrentEditorokhttp(), this)) {
                    return Okio.blackhole();
                }
                if (!this.entry.getReadableokhttp()) {
                    final boolean[] writtenokhttp = this.getWrittenokhttp();
                    checkNotNull(writtenokhttp);
                    writtenokhttp[i2] = true;
                }
                return new FaultHidingSink(diskLruCache.getFileSystemokhttp().sink(this.entry.getDirtyFilesokhttp().get(i2)), new Function1<IOException, Unit>() {
                    public Unit invoke(final Object iOException) {
                        this.invoke2((IOException) iOException);
                        return Unit.INSTANCE;
                    }
                    public void detachokhttp() {
                    }

                    public void invoke2(final IOException it) {
                        checkNotNullParameter(it, "it");
                        final DiskLruCache diskLruCache2 = diskLruCache;
                        final Function1<IOException, Unit> editor = this;
                        synchronized (diskLruCache2) {
                            editor.detachokhttp();
                            final Unit unit = Unit.INSTANCE;
                        }
                    }
                });
            }
        }
        public void commit() throws IOException {
            final DiskLruCache diskLruCache = this0;
            synchronized (diskLruCache) {
                try {
                    if (done) {
                        throw new IllegalStateException("Check failed.");
                    }
                    if (areEqual(this.entry.getCurrentEditorokhttp(), this)) {
                        diskLruCache.completeEditokhttp(this, true);
                    }
                    done = true;
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    throw th;
                }
            }
        }
        public void abort() throws IOException {
            final DiskLruCache diskLruCache = this0;
            synchronized (diskLruCache) {
                try {
                    if (done) {
                        throw new IllegalStateException("Check failed.");
                    }
                    if (areEqual(this.entry.getCurrentEditorokhttp(), this)) {
                        diskLruCache.completeEditokhttp(this, false);
                    }
                    done = true;
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    throw th;
                }
            }
        }
    }
    public final class Entry {
        final DiskLruCache this0;
        private final List<File> cleanFiles;
        private final List<File> dirtyFiles;
        private final String key;
        private final long[] lengths;
        private Editor currentEditor;
        private int lockingSourceCount;
        private boolean readable;
        private long sequenceNumber;
        private boolean zombie;
        public Entry(final DiskLruCache this0, final String key) {
            checkNotNullParameter(this0, "this0");
            checkNotNullParameter(key, "key");
            this.this0 = this0;
            this.key = key;
            lengths = new long[this0.getValueCountokhttp()];
            cleanFiles = new ArrayList();
            dirtyFiles = new ArrayList();
            final StringBuilder sb = new StringBuilder(key);
            sb.append('.');
            final int length = sb.length();
            final int valueCountokhttp = this0.getValueCountokhttp();
            for (int i2 = 0; i2 < valueCountokhttp; i2++) {
                sb.append(i2);
                cleanFiles.add(new File(this.this0.getDirectory(), sb.toString()));
                sb.append(".tmp");
                dirtyFiles.add(new File(this.this0.getDirectory(), sb.toString()));
                sb.setLength(length);
            }
        }
        public String getKeyokhttp() {
            return key;
        }
        public long[] getLengthsokhttp() {
            return lengths;
        }
        public void setLengthsokhttp(List<String> strings) throws IOException {
            checkNotNullParameter(strings, "strings");
            if (strings.size() != this.this0.getValueCountokhttp()) {
                invalidLengths(strings);
                throw new KotlinNothingValueException();
            }
            try {
                int size = strings.size();
                int i2 = 0;
                while (i2 < size) {
                    int i3 = i2 + 1;
                    this.lengths[i2] = Long.parseLong(strings.get(i2));
                    i2 = i3;
                }
            } catch (NumberFormatException unused) {
                invalidLengths(strings);
                throw new KotlinNothingValueException();
            }
        }
        public List<File> getCleanFilesokhttp() {
            return cleanFiles;
        }
        public List<File> getDirtyFilesokhttp() {
            return dirtyFiles;
        }
        public boolean getReadableokhttp() {
            return readable;
        }
        public void setReadableokhttp(final boolean z) {
            readable = z;
        }
        public boolean getZombieokhttp() {
            return zombie;
        }
        public void setZombieokhttp(final boolean z) {
            zombie = z;
        }
        public Editor getCurrentEditorokhttp() {
            return currentEditor;
        }
        public void setCurrentEditorokhttp(final Editor editor) {
            currentEditor = editor;
        }
        public int getLockingSourceCountokhttp() {
            return lockingSourceCount;
        }
        public void setLockingSourceCountokhttp(final int i2) {
            lockingSourceCount = i2;
        }
        public long getSequenceNumberokhttp() {
            return sequenceNumber;
        }
        public void setSequenceNumberokhttp(final long j2) {
            sequenceNumber = j2;
        }
        public void writeLengthsokhttp(final BufferedSink writer) throws IOException {
            checkNotNullParameter(writer, "writer");
            final long[] jArr = lengths;
            final int length = jArr.length;
            int i2 = 0;
            while (i2 < length) {
                final long j2 = jArr[i2];
                i2++;
                writer.writeByte(32).writeDecimalLong(j2);
            }
        }
        private Void invalidLengths(final List<String> list) throws IOException {
            throw new IOException(stringPlus("unexpected journal line: ", list));
        }
        public Snapshot snapshotokhttp() {
            final DiskLruCache diskLruCache = this0;
            if (Util.assertionsEnabled && !Thread.holdsLock(diskLruCache)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + diskLruCache);
            }
            if (!readable) {
                return null;
            }
            if (!this0.civilizedFileSystem && (null != this.currentEditor || zombie)) {
                return null;
            }
            final ArrayList arrayList = new ArrayList();
            final long[] jArr = lengths.clone();
            try {
                final int valueCountokhttp = this0.getValueCountokhttp();
                for (int i2 = 0; i2 < valueCountokhttp; i2++) {
                    arrayList.add(this.newSource(i2));
                }
                return new Snapshot(this0, key, sequenceNumber, arrayList, jArr);
            } catch (final FileNotFoundException unused) {
                final Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Util.closeQuietly((Source) it.next());
                }
                try {
                    this0.removeEntryokhttp();
                } catch (final IOException unused2) {
                }
                return null;
            }
        }
        private Source newSource(final int i2) throws FileNotFoundException {
            Source source = (Source) this0.getFileSystemokhttp().source(cleanFiles.get(i2));
            if (this0.civilizedFileSystem) {
                return source;
            }
            lockingSourceCount++;
            DiskLruCache diskLruCache = this0;
            return new ForwardingSource(diskLruCache, this) { 
                final DiskLruCache this0;
                final ForwardingSource this1;
                private boolean closed;
                 { 
                    this0 = diskLruCache;
                    this1 = this;
                } 
                public void close() throws IOException {
                    super.close();
                    if (closed) {
                        return;
                    }
                    closed = true;
                    final DiskLruCache diskLruCache2 = this0;
                    final ForwardingSource entry = this1;
                    synchronized (diskLruCache2) {
                        try {
                            entry.timeout(entry.getLockingSourceCountokhttp() - 1);
                            if (0 == entry.getLockingSourceCountokhttp() && entry.getZombieokhttp()) {
                                diskLruCache2.removeEntryokhttp();
                            }
                            final Unit unit = Unit.INSTANCE;
                        } catch (final Throwable th) {
                            throw th;
                        }
                    }
                }
            };
        }
    }
}
