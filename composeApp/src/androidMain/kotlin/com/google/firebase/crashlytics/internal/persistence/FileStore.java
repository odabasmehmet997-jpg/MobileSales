package com.google.firebase.crashlytics.internal.persistence;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*  INFO: loaded from: classes2.dex */
public class FileStore {
    private static final String FILES_PATH = ".com.google.firebase.crashlytics.files.v1";
    private static final String NATIVE_REPORTS_PATH = "native-reports";
    private static final String NATIVE_SESSION_SUBDIR = "native";
    private static final String PRIORITY_REPORTS_PATH = "priority-reports";
    private static final String REPORTS_PATH = "reports";
    private static final String SESSIONS_PATH = "open-sessions";
    private final File nativeReportsDir;
    private final File priorityReportsDir;
    private final File reportsDir;
    private final File rootDir;
    private final File sessionsDir;

    public FileStore(Context context) {
        File filePrepareBaseDir = prepareBaseDir(new File(context.getFilesDir(), FILES_PATH));
        this.rootDir = filePrepareBaseDir;
        this.sessionsDir = prepareBaseDir(new File(filePrepareBaseDir, SESSIONS_PATH));
        this.reportsDir = prepareBaseDir(new File(filePrepareBaseDir, REPORTS_PATH));
        this.priorityReportsDir = prepareBaseDir(new File(filePrepareBaseDir, PRIORITY_REPORTS_PATH));
        this.nativeReportsDir = prepareBaseDir(new File(filePrepareBaseDir, NATIVE_REPORTS_PATH));
    }

    @VisibleForTesting
    public void deleteAllCrashlyticsFiles() {
        recursiveDelete(this.rootDir);
    }

    public void cleanupLegacyFiles() {
        File[] fileArr = {new File(this.rootDir.getParent(), ".com.google.firebase.crashlytics"), new File(this.rootDir.getParent(), ".com.google.firebase.crashlytics-ndk")};
        for (int i2 = 0; i2 < 2; i2++) {
            File file = fileArr[i2];
            if (file.exists() && recursiveDelete(file)) {
                Logger.getLogger().d("Deleted legacy Crashlytics files from " + file.getPath());
            }
        }
    }

    static boolean recursiveDelete(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                recursiveDelete(file2);
            }
        }
        return file.delete();
    }

    public File getCommonFile(String str) {
        return new File(this.rootDir, str);
    }

    public List<File> getCommonFiles(FilenameFilter filenameFilter) {
        return safeArrayToList(this.rootDir.listFiles(filenameFilter));
    }

    private File getSessionDir(String str) {
        return prepareDir(new File(this.sessionsDir, str));
    }

    public File getSessionFile(String str, String str2) {
        return new File(getSessionDir(str), str2);
    }

    public List<File> getSessionFiles(String str, FilenameFilter filenameFilter) {
        return safeArrayToList(getSessionDir(str).listFiles(filenameFilter));
    }

    public File getNativeSessionDir(String str) {
        return prepareDir(new File(getSessionDir(str), NATIVE_SESSION_SUBDIR));
    }

    public boolean deleteSessionFiles(String str) {
        return recursiveDelete(new File(this.sessionsDir, str));
    }

    public List<String> getAllOpenSessionIds() {
        return safeArrayToList(this.sessionsDir.list());
    }

    public File getReport(String str) {
        return new File(this.reportsDir, str);
    }

    public List<File> getReports() {
        return safeArrayToList(this.reportsDir.listFiles());
    }

    public File getPriorityReport(String str) {
        return new File(this.priorityReportsDir, str);
    }

    public List<File> getPriorityReports() {
        return safeArrayToList(this.priorityReportsDir.listFiles());
    }

    public File getNativeReport(String str) {
        return new File(this.nativeReportsDir, str);
    }

    public List<File> getNativeReports() {
        return safeArrayToList(this.nativeReportsDir.listFiles());
    }

    private static File prepareDir(File file) {
        file.mkdirs();
        return file;
    }

    private static synchronized File prepareBaseDir(File file) {
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    return file;
                }
                Logger.getLogger().d("Unexpected non-directory file: " + file + "; deleting file and creating new directory.");
                file.delete();
            }
            if (file.mkdirs()) {
                return file;
            }
            throw new IllegalStateException("Could not create Crashlytics-specific directory: " + file);
        } catch (Throwable th) {
            throw th;
        }
    }

    private static <T> List<T> safeArrayToList(@Nullable T[] tArr) {
        return tArr == null ? Collections.emptyList() : Arrays.asList(tArr);
    }
}
