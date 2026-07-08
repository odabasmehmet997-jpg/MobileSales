package javax.mail;

import java.util.Vector;
import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import javax.mail.event.StoreEvent;
import javax.mail.event.StoreListener;

public abstract class Store extends Service {
    private volatile Vector folderListeners;
    private volatile Vector storeListeners;
    public abstract Folder getDefaultFolder() throws MessagingException;
    public abstract Folder getFolder(String str) throws MessagingException;
    public abstract Folder getFolder(URLName uRLName) throws MessagingException;
    protected Store(Session session, URLName uRLName) {
        super(session, uRLName);
    }
    public Folder[] getPersonalNamespaces() throws MessagingException {
        return new Folder[]{getDefaultFolder()};
    }
    public Folder[] getUserNamespaces(String str) throws MessagingException {
        return new Folder[0];
    }
    public Folder[] getSharedNamespaces() throws MessagingException {
        return new Folder[0];
    }
    public synchronized void addStoreListener(StoreListener storeListener) {
        if (null == storeListeners) {
            this.storeListeners = new Vector();
        }
        this.storeListeners.addElement(storeListener);
    }
    public synchronized void removeStoreListener(StoreListener storeListener) {
        if (null != storeListeners) {
            this.storeListeners.removeElement(storeListener);
        }
    }
    public void notifyStoreListeners(int i2, String str) {
        if (null != storeListeners) {
            queueEvent(new StoreEvent(this, i2, str), this.storeListeners);
        }
    }
    public synchronized void addFolderListener(FolderListener folderListener) {
        try {
            if (null == folderListeners) {
                this.folderListeners = new Vector();
            }
            this.folderListeners.addElement(folderListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void removeFolderListener(FolderListener folderListener) {
        if (null != folderListeners) {
            this.folderListeners.removeElement(folderListener);
        }
    }
    public void notifyFolderListeners(int i2, Folder folder) {
        if (null != folderListeners) {
            queueEvent(new FolderEvent(this, folder, i2), this.folderListeners);
        }
    }
    public void notifyFolderRenamedListeners(Folder folder, Folder folder2) {
        if (null != folderListeners) {
            queueEvent(new FolderEvent(this, folder, folder2, 3), this.folderListeners);
        }
    }
}
