package javax.mail;

public class StoreClosedException extends MessagingException {
    private static final long serialVersionUID = -3145392336120082655L;
    private final transient Store store;
    public StoreClosedException(Store store2) {
        this(store2, null);
    }
    public StoreClosedException(Store store2, String str) {
        super(str);
        this.store = store2;
    }
    public Store getStore() {
        return this.store;
    }
}
