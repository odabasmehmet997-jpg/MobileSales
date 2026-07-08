package javax.mail.event;

public abstract class ConnectionAdapter implements ConnectionListener {
    public void closed(ConnectionEvent connectionEvent) {}
    public void disconnected(ConnectionEvent connectionEvent) {}
    public void opened(ConnectionEvent connectionEvent) {}
}
