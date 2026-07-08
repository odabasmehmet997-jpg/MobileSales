package javax.mail;

public class FolderClosedException extends MessagingException {
    private static final long serialVersionUID = 1687879213433302315L;
    private final transient Folder folder;
    public FolderClosedException(Folder folder2) {
        this(folder2, null);
    }
    public FolderClosedException(Folder folder2, String str) {
        super(str);
        this.folder = folder2;
    }
    public Folder getFolder() {
        return this.folder;
    }
    @Override
    public String getMessage() {
        return super.getMessage() + " Folder: " + this.folder.getName();
    }
    @Override
    public String toString() {
        return "FolderClosedException: " + this.folder.getName();
    }
}
