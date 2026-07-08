package javax.mail;

public class ReadOnlyFolderException extends MessagingException {
    private static final long serialVersionUID = 5711829372799039325L;
    private final transient Folder folder;

    public ReadOnlyFolderException(Folder folder2) {
        this(folder2, null);
    }

    public ReadOnlyFolderException(Folder folder2, String str) {
        super(str);
        this.folder = folder2;
    }

    public Folder getFolder() {
        return this.folder;
    }
}
