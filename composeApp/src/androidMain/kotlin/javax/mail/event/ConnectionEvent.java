package javax.mail.event;

public class ConnectionEvent extends MailEvent {
    public static final int CLOSED = 3;
    public static final int DISCONNECTED = 2;
    public static final int OPENED = 1;
    public static final int FOLDER_DELETED = 4;
    private static final long serialVersionUID = -1855480171284792957L;
    protected int type;
    public ConnectionEvent(Object obj, int i2) {
        super(obj);
        this.type = i2;
    }
    public int getType() {
        return this.type;
    }
    public void dispatch(Object obj) {
        int i2 = this.type;
        if (1 == i2) {
            ((ConnectionListener) obj).opened(this);
        } else if (2 == i2) {
            ((ConnectionListener) obj).disconnected(this);
        } else if (ConnectionEvent.FOLDER_DELETED == i2) {
            ((ConnectionListener) obj).closed(this);
        }
    }
}
