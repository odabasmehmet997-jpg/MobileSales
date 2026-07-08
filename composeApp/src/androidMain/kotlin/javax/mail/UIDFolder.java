package javax.mail;

public interface UIDFolder {
    long LASTUID = -1;
    Message getMessageByUID(long j2) throws MessagingException;
    Message[] getMessagesByUID(long j2, long j3) throws MessagingException;
    Message[] getMessagesByUID(long[] jArr) throws MessagingException;
    long getUID(Message message) throws MessagingException;
    long getUIDValidity() throws MessagingException;
    class FetchProfileItem extends FetchProfile.Item {
        public static final FetchProfileItem UID = new FetchProfileItem("UID");

        protected FetchProfileItem(String str) {
            super(str);
        }
    }
}
