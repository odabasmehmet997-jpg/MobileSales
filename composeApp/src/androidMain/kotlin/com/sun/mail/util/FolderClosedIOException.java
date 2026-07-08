package com.sun.mail.util;

import javax.mail.Folder;
import java.io.IOException;

public class FolderClosedIOException extends IOException {
    private static final long serialVersionUID = 4281122580365555735L;
    private final transient Folder folder;

    public FolderClosedIOException(Folder folder2) {
        this(folder2, null);
    }

    public FolderClosedIOException(Folder folder2, String str) {
        super(str);
        this.folder = folder2;
    }

    public Folder getFolder() {
        return this.folder;
    }
}
