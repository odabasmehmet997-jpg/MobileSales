package javax.mail;

import java.util.Vector;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import javax.mail.event.MailEvent;
import javax.mail.event.MessageChangedEvent;
import javax.mail.event.MessageChangedListener;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import javax.mail.search.SearchTerm;

public abstract class Folder {
    public static final int HOLDS_FOLDERS = 2;
    public static final int HOLDS_MESSAGES = 1;
    public static final int READ_ONLY = 1;
    public static final int READ_WRITE = 2;
    private volatile Vector connectionListeners;
    private volatile Vector folderListeners;
    private volatile Vector messageChangedListeners;
    private volatile Vector messageCountListeners;
    protected int mode = -1;
    private EventQueue q;
    private final Object qLock = new Object();
    protected Store store;
    public abstract void appendMessages(Message[] messageArr) throws MessagingException;
    public abstract void close(boolean z) throws MessagingException;
    public abstract boolean create(int mode) throws MessagingException;
    public abstract boolean delete(boolean z) throws MessagingException;
    public abstract boolean exists() throws MessagingException;
    public abstract Message[] expunge() throws MessagingException;
    public void fetch(Message[] messageArr, FetchProfile fetchProfile) throws MessagingException {
    }
    public abstract Folder getFolder(String name) throws MessagingException;
    public abstract String getFullName();
    public abstract Message getMessage(int msgNumber) throws MessagingException;
    public abstract int getMessageCount() throws MessagingException;
    public abstract String getName();
    public abstract Folder getParent() throws MessagingException;
    public abstract Flags getPermanentFlags();
    public abstract char getSeparator() throws MessagingException;
    public abstract int getType() throws MessagingException;
    public abstract boolean hasNewMessages() throws MessagingException;
    public abstract boolean isOpen();
    public boolean isSubscribed() {
        return true;
    }
    public abstract Folder[] list(String pattern) throws MessagingException;
    public abstract void open(int mode) throws MessagingException;
    public abstract boolean renameTo(Folder folder) throws MessagingException;
    protected Folder(Store store2) {
        this.store = store2;
    }
    public URLName getURLName() throws MessagingException {
        URLName uRLName = store.getURLName();
        String fullName = getFullName();
        StringBuffer stringBuffer = new StringBuffer();
        if (fullName != null) {
            stringBuffer.append(fullName);
        }
        return new URLName(uRLName.getProtocol(), uRLName.getHost(), uRLName.getPort(), stringBuffer.toString(), uRLName.getUsername(), null);
    }
    public Store getStore() {
        return this.store;
    }
    public Folder[] listSubscribed(String pattern) throws MessagingException {
        return list(pattern);
    }
    public Folder[] list() throws MessagingException {
        return list("%");
    }
    public Folder[] listSubscribed() throws MessagingException {
        return listSubscribed("%");
    }
    public void setSubscribed(boolean subscribed) throws MessagingException {
        throw new MethodNotSupportedException();
    }
    public synchronized int getMode() {
        if (isOpen()) {
        } else {
            throw new IllegalStateException("Folder not open");
        }
        return this.mode;
    }
    public synchronized int getNewMessageCount() throws MessagingException {
        if (!isOpen()) {
            return -1;
        }
        int messageCount = getMessageCount();
        int newMessageCount = 0;
        for (int i = 1; i <= messageCount; i++) {
            try {
                if (getMessage(i).isSet(Flags.Flag.RECENT)) {
                    newMessageCount++;
                }
            } catch (MessageRemovedException unused) {
            }
        }
        return newMessageCount;
    }
    public synchronized int getUnreadMessageCount() throws MessagingException {
        if (!isOpen()) {
            return -1;
        }
        int messageCount = getMessageCount();
        int unreadMessageCount = 0;
        for (int i = 1; i <= messageCount; i++) {
            try {
                if (!getMessage(i).isSet(Flags.Flag.SEEN)) {
                    unreadMessageCount++;
                }
            } catch (MessageRemovedException unused) {
            }
        }
        return unreadMessageCount;
    }
    public synchronized int getDeletedMessageCount() throws MessagingException {
        if (!isOpen()) {
            return -1;
        }
        int messageCount = getMessageCount();
        int deletedMessageCount = 0;
        for (int i = 1; i <= messageCount; i++) {
            try {
                if (getMessage(i).isSet(Flags.Flag.DELETED)) {
                    deletedMessageCount++;
                }
            } catch (MessageRemovedException unused) {
            }
        }
        return deletedMessageCount;
    }
    public synchronized Message[] getMessages(int startIndex, int endIndex) throws MessagingException {
        Message[] messageArr;
        messageArr = new Message[(endIndex - startIndex) + 1];
        for (int i = startIndex; i <= endIndex; i++) {
            messageArr[i - startIndex] = getMessage(i);
        }
        return messageArr;
    }
    public synchronized Message[] getMessages(int[] indices) throws MessagingException {
        Message[] messageArr;
        int length = indices.length;
        messageArr = new Message[length];
        for (int i = 0; i < length; i++) {
            messageArr[i] = getMessage(indices[i]);
        }
        return messageArr;
    }
    public synchronized Message[] getMessages() throws MessagingException {
        Message[] messageArr;
        if (isOpen()) {
            int messageCount = getMessageCount();
            messageArr = new Message[messageCount];
            for (int i = 1; i <= messageCount; i++) {
                messageArr[i - 1] = getMessage(i);
            }
        } else {
            throw new IllegalStateException("Folder not open");
        }
        return messageArr;
    }
    public synchronized void setFlags(Message[] messages, Flags flags, boolean set) throws MessagingException {
        for (int i = 0; i < messages.length; i++) {
            try {
                messages[i].setFlags(flags, set);
            } catch (MessageRemovedException unused) {
            }
        }
    }
    public synchronized void setFlags(int startIndex, int endIndex, Flags flags, boolean set) throws MessagingException {
        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Start index must be less than or equal to end index");
        }
        if (startIndex < 1 || endIndex > getMessageCount()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Folder.setFlags(int, int, javax.mail.Flags, boolean):void");
    }
    public synchronized void setFlags(int[] indices, Flags flags, boolean set) throws MessagingException {
        for (int i = 0; i < indices.length; i++) {
            try {
                getMessage(indices[i]).setFlags(flags, set);
            } catch (MessageRemovedException unused) {
            }
        }
    }
    public void copyMessages(Message[] messages, Folder destinationFolder) throws MessagingException {
        if (destinationFolder.exists()) {
            destinationFolder.appendMessages(messages);
            return;
        }
        final String stringBuffer = destinationFolder.getFullName() +
                " does not exist";
        throw new FolderNotFoundException(stringBuffer, destinationFolder);
    }
    public Message[] search(SearchTerm searchTerm) throws MessagingException {
        return search(searchTerm, getMessages());
    }
    public Message[] search(SearchTerm searchTerm, Message[] messages) throws MessagingException {
        Vector vector = new Vector();
        for (int i = 0; i < messages.length; i++) {
            try {
                if (messages[i].match(searchTerm)) {
                    vector.addElement(messages[i]);
                }
            } catch (MessageRemovedException unused) {
            }
        }
        Message[] messageArray = new Message[vector.size()];
        vector.copyInto(messageArray);
        return messageArray;
    }
    public synchronized void addConnectionListener(ConnectionListener connectionListener) {
        try {
            if (connectionListeners == null) {
                connectionListeners = new Vector();
            }
            connectionListeners.addElement(connectionListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void removeConnectionListener(ConnectionListener connectionListener) {
        if (connectionListeners != null) {
            connectionListeners.removeElement(connectionListener);
        }
    }
    public void notifyConnectionListeners(int event) {
        if (connectionListeners != null) {
            queueEvent(new ConnectionEvent(this, event), connectionListeners);
        }
        if (ConnectionEvent.FOLDER_DELETED == event) {
            terminateQueue();
        }
    }
    public synchronized void addFolderListener(FolderListener folderListener) {
        try {
            if (folderListeners == null) {
                folderListeners = new Vector();
            }
            folderListeners.addElement(folderListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void removeFolderListener(FolderListener folderListener) {
        if (folderListeners != null) {
            folderListeners.removeElement(folderListener);
        }
    }
    public void notifyFolderListeners(int event) {
        if (folderListeners != null) {
            queueEvent(new FolderEvent(this, this, event), folderListeners);
        }
        this.store.notifyFolderListeners(event, this);
    }
    public void notifyFolderRenamedListeners(Folder folder) {
        if (folderListeners != null) {
            queueEvent(new FolderEvent(this, this, folder, FolderEvent.RENAMED), folderListeners);
        }
        this.store.notifyFolderRenamedListeners(this, folder);
    }
    public synchronized void addMessageCountListener(MessageCountListener messageCountListener) {
        try {
            if (messageCountListeners == null) {
                messageCountListeners = new Vector();
            }
            messageCountListeners.addElement(messageCountListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void removeMessageCountListener(MessageCountListener messageCountListener) {
        if (messageCountListeners != null) {
            messageCountListeners.removeElement(messageCountListener);
        }
    }
    public void notifyMessageAddedListeners(Message[] messages) {
        if (messageCountListeners != null) {
            queueEvent(new MessageCountEvent(this, 1, false, messages), messageCountListeners);
        }
    }
    public void notifyMessageRemovedListeners(boolean removed, Message[] messages) {
        if (messageCountListeners != null) {
            queueEvent(new MessageCountEvent(this, 2, removed, messages), messageCountListeners);
        }
    }
    public synchronized void addMessageChangedListener(MessageChangedListener messageChangedListener) {
        try {
            if (messageChangedListeners == null) {
                messageChangedListeners = new Vector();
            }
            messageChangedListeners.addElement(messageChangedListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void removeMessageChangedListener(MessageChangedListener messageChangedListener) {
        if (messageChangedListeners != null) {
            messageChangedListeners.removeElement(messageChangedListener);
        }
    }
    public void notifyMessageChangedListeners(int event, Message message) {
        if (messageChangedListeners != null) {
            queueEvent(new MessageChangedEvent(this, event, message), messageChangedListeners);
        }
    }
    private void queueEvent(MailEvent mailEvent, Vector vector) {
        synchronized (this.qLock) {
            try {
                if (vector == null) {
                    this.q = new EventQueue();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.q.enqueue(mailEvent, (Vector) vector.clone());
    }
    static class TerminatorEvent extends MailEvent {
        private static final long serialVersionUID = 3765761925441296565L;

        TerminatorEvent() {
            super(new Object());
        }

        public void dispatch(Object obj) {
            Thread.currentThread().interrupt();
        }
    }
    private void terminateQueue() {
        synchronized (this.qLock) {
            try {
                if (null != q) {
                    Vector vector = new Vector();
                    vector.setSize(1);
                    this.q.enqueue(new TerminatorEvent(), vector);
                    this.q = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    protected void finalize() throws Throwable {
        super.finalize();
        terminateQueue();
    }
    public String toString() {
        String fullName = getFullName();
        if (null != fullName) {
            return fullName;
        }
        return super.toString();
    }
}
