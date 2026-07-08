package javax.mail.event;

public abstract class TransportAdapter implements TransportListener {
    public void messageDelivered(TransportEvent transportEvent) {
    }

    public void messageNotDelivered(TransportEvent transportEvent) {
    }

    public void messagePartiallyDelivered(TransportEvent transportEvent) {
    }
}
