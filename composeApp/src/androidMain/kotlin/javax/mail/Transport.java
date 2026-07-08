package javax.mail;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;

public abstract class Transport extends Service {
    private volatile Vector transportListeners;
    public abstract void sendMessage(Message message, Address[] addressArr) throws MessagingException;
    protected Transport(Session session, URLName uRLName) {
        super(session, uRLName);
    }
    public static void send(Message message) throws MessagingException {
        message.saveChanges();
        send0(message, message.getAllRecipients());
    }
    public static void send(Message message, Address[] addressArr) throws MessagingException {
        message.saveChanges();
        send0(message, addressArr);
    }
    private static void send0(Message message, Address[] addressArr) throws MessagingException {
        Address[] addressArr2;
        Address[] addressArr3;
        Message message2 = message;
        Address[] addressArr4 = addressArr;
        if (null == addressArr4 || 0 == addressArr4.length) {
            throw new SendFailedException("No recipient addresses");
        }
        Hashtable hashtable = new Hashtable();
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i2 = 0; i2 < addressArr4.length; i2++) {
            if (hashtable.containsKey(addressArr4[i2].getType())) {
                ((Vector) hashtable.get(addressArr4[i2].getType())).addElement(addressArr4[i2]);
            } else {
                Vector vector4 = new Vector();
                vector4.addElement(addressArr4[i2]);
                hashtable.put(addressArr4[i2].getType(), vector4);
            }
        }
        int size = hashtable.size();
        if (0 != size) {
            Session session = message2.session;
            Address[] addressArr5 = null;
            if (null == session) {
                session = Session.getDefaultInstance(System.getProperties(), null);
            }
            if (1 == size) {
                Transport transport = session.getTransport(addressArr4[0]);
                try {
                    transport.connect();
                    transport.sendMessage(message2, addressArr4);
                } finally {
                    transport.close();
                }
            } else {
                final Iterator iterator = hashtable.values().iterator();
                boolean z = false;
                SendFailedException sendFailedException = null;
                while (iterator.hasNext()) {
                    Vector vector5 = (Vector) iterator.next();
                    int size2 = vector5.size();
                    Address[] addressArr6 = new Address[size2];
                    vector5.copyInto(addressArr6);
                    Transport transport2 = session.getTransport(addressArr6[0]);
                    if (null == transport2) {
                        for (int i3 = 0; i3 < size2; i3++) {
                            vector.addElement(addressArr6[i3]);
                        }
                    } else {
                        try {
                            transport2.connect();
                            transport2.sendMessage(message2, addressArr6);
                            transport2.close();
                        } catch (SendFailedException e2) {
                            if (null == sendFailedException) {
                                sendFailedException = e2;
                            } else {
                                sendFailedException.setNextException(e2);
                            }
                            Address[] invalidAddresses = e2.getInvalidAddresses();
                            if (null != invalidAddresses) {
                                for (Address addElement : invalidAddresses) {
                                    vector.addElement(addElement);
                                }
                            }
                            Address[] validSentAddresses = e2.getValidSentAddresses();
                            if (null != validSentAddresses) {
                                for (Address addElement2 : validSentAddresses) {
                                    vector2.addElement(addElement2);
                                }
                            }
                            Address[] validUnsentAddresses = e2.getValidUnsentAddresses();
                            if (null != validUnsentAddresses) {
                                for (Address addElement3 : validUnsentAddresses) {
                                    vector3.addElement(addElement3);
                                }
                            }
                            transport2.close();
                            z = true;
                        } catch (MessagingException e3) {
                            if (null == sendFailedException) {
                                sendFailedException = (SendFailedException) e3;
                            } else {
                                sendFailedException.setNextException(e3);
                            }
                            transport2.close();
                            z = true;
                        } catch (Throwable th) {
                            transport2.close();
                            throw th;
                        }
                    }
                }
                if (z || 0 != vector.size() || 0 != vector3.size()) {
                    if (0 < vector2.size()) {
                        Address[] addressArr7 = new Address[vector2.size()];
                        vector2.copyInto(addressArr7);
                        addressArr2 = addressArr7;
                    } else {
                        addressArr2 = null;
                    }
                    if (0 < vector3.size()) {
                        Address[] addressArr8 = new Address[vector3.size()];
                        vector3.copyInto(addressArr8);
                        addressArr3 = addressArr8;
                    } else {
                        addressArr3 = null;
                    }
                    if (0 < vector.size()) {
                        addressArr5 = new Address[vector.size()];
                        vector.copyInto(addressArr5);
                    }
                    throw new SendFailedException("Sending failed", sendFailedException, addressArr2, addressArr3, addressArr5);
                }
            }
        } else {
            throw new SendFailedException("No recipient addresses");
        }
    }
    public synchronized void addTransportListener(TransportListener transportListener) {
        try {
            if (null == transportListeners) {
                this.transportListeners = new Vector();
            }
            this.transportListeners.addElement(transportListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void removeTransportListener(TransportListener transportListener) {
        if (null != transportListeners) {
            this.transportListeners.removeElement(transportListener);
        }
    }
    public void notifyTransportListeners(int i2, Address[] addressArr, Address[] addressArr2, Address[] addressArr3, Message message) {
        if (null != transportListeners) {
            queueEvent(new TransportEvent(this, i2, addressArr, addressArr2, addressArr3, message), this.transportListeners);
        }
    }
}
