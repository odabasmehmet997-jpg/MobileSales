package org.apache.harmony.awt.datatransfer;


public class DataTransferThread extends Thread {
    private final DTK dtk;

    public DataTransferThread(DTK dtk) {
        super("AWT-DataTransferThread");
        setDaemon(true);
        this.dtk = dtk;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        synchronized (this) {
            this.dtk.initDragAndDrop();
            notifyAll();
        }
        this.dtk.runEventLoop();
    }

    @Override // java.lang.Thread
    public void start() {
        synchronized (this) {
            try {
                super.start();
                try {
                    wait();
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
