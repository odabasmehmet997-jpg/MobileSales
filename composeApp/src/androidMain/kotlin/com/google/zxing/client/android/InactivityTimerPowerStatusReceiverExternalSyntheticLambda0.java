package com.google.zxing.client.android;

record InactivityTimerPowerStatusReceiverExternalSyntheticLambda0( InactivityTimer.PowerStatusReceiver f0, boolean f1) implements Runnable {
    public void run() {
        f0.lambdaonReceive0(f1);
    }
}
