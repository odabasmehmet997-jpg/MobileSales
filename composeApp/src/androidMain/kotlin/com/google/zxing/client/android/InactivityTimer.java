package com.google.zxing.client.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import androidx.work.PeriodicWorkRequest;

public final class InactivityTimer {
    private final Runnable callback;
    private final Context context;
    
    public Handler handler;
    private boolean onBattery;
    private final BroadcastReceiver powerStatusReceiver;
    private boolean registered;

    public InactivityTimer(final Context context2, final Runnable runnable) {
        context = context2;
        callback = runnable;
        powerStatusReceiver = new PowerStatusReceiver();
        handler = new Handler();
    }

    public void activity() {
        this.cancelCallback();
        if (onBattery) {
            handler.postDelayed(callback, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
        }
    }

    public void start() {
        this.registerReceiver();
        this.activity();
    }

    public void cancel() {
        this.cancelCallback();
        this.unregisterReceiver();
    }

    private void unregisterReceiver() {
        if (registered) {
            context.unregisterReceiver(powerStatusReceiver);
            registered = false;
        }
    }

    private void registerReceiver() {
        if (!registered) {
            context.registerReceiver(powerStatusReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            registered = true;
        }
    }

    private void cancelCallback() {
        handler.removeCallbacksAndMessages(null);
    }

    
    public void onBattery(final boolean z) {
        onBattery = z;
        if (registered) {
            this.activity();
        }
    }

    final class PowerStatusReceiver extends BroadcastReceiver {
        private PowerStatusReceiver() {
        }

        public void onReceive(final Context context, final Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                handler.post(new InactivityTimerPowerStatusReceiverExternalSyntheticLambda0(this, 0 >= intent.getIntExtra("plugged", -1)));
            }
        }

        
        public void lambdaonReceive0(final boolean z) {
            onBattery(z);
        }
    }
}
