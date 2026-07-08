package javax.mail;

import java.util.Vector;
import javax.mail.event.MailEvent;

class EventQueue implements Runnable {
    private QueueElement head;
    private Thread qThread;
    private QueueElement tail;

    @Override
    public void run() {

    }

    static class QueueElement {
        MailEvent event;
        QueueElement next;
        QueueElement prev;
        Vector vector;

        QueueElement(MailEvent mailEvent, Vector vector2) {
            this.event = mailEvent;
            this.vector = vector2;
        }
    }
    public EventQueue() {
        Thread thread = new Thread(this, "JavaMail-EventQueue");
        this.qThread = thread;
        thread.setDaemon(true);
        this.qThread.start();
    }
    public synchronized void enqueue(MailEvent mailEvent, Vector vector) {
        try {
            QueueElement queueElement = new QueueElement(mailEvent, vector);
            QueueElement queueElement2 = this.head;
            if (null == queueElement2) {
                this.head = queueElement;
                this.tail = queueElement;
            } else {
                queueElement.next = queueElement2;
                queueElement2.prev = queueElement;
                this.head = queueElement;
            }
            notifyAll();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    private synchronized QueueElement dequeue() throws InterruptedException {
        QueueElement queueElement;
        while (true) {
            try {
                queueElement = this.tail;
                if (null != queueElement) {
                    break;
                }
                wait();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        QueueElement queueElement2 = queueElement.prev;
        this.tail = queueElement2;
        if (null == queueElement2) {
            this.head = null;
        } else {
            queueElement2.next = null;
        }
        queueElement.next = null;
        queueElement.prev = null;
        return queueElement;
    }

    public void stop() {
        Thread thread = this.qThread;
        if (null != thread) {
            thread.interrupt();
            this.qThread = null;
        }
    }
}
