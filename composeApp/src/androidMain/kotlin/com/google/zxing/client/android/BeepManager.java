package com.google.zxing.client.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import java.io.IOException;

public final class BeepManager {
    private static final String TAG = "BeepManager";
    private boolean beepEnabled = true;
    private final Context context;
    private final boolean vibrateEnabled;

    public BeepManager(final Activity activity) {
        activity.setVolumeControlStream(3);
        context = activity.getApplicationContext();
    }

    public void setBeepEnabled(final boolean z) {
        beepEnabled = z;
    }

    @SuppressLint("MissingPermission")
    public synchronized void playBeepSoundAndVibrate() {
        final Vibrator vibrator;
        try {
            if (beepEnabled) {
                this.playBeepSound();
            }
            if (vibrateEnabled && null != (vibrator = (Vibrator) this.context.getSystemService("vibrator"))) {
                vibrator.vibrate(200);
            }
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public MediaPlayer playBeepSound() {
        AssetFileDescriptor openRawResourceFd;
        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(2).build());
        mediaPlayer.setOnCompletionListener(new BeepManagerExternalSyntheticLambda0());
        mediaPlayer.setOnErrorListener(new BeepManagerExternalSyntheticLambda1());
        try {
            openRawResourceFd = context.getResources().openRawResourceFd(R.raw.zxing_beep);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.setVolume(0.1f, 0.1f);
            mediaPlayer.prepare();
            mediaPlayer.start();
            return mediaPlayer;
        } catch (final IOException e2) {
            Log.w(BeepManager.TAG, e2);
            mediaPlayer.reset();
            mediaPlayer.release();
            return null;
        } catch (final Throwable th) {
            openRawResourceFd.close();
            throw th;
        }
    }

    
    public static void lambdaplayBeepSound0(final MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
    }

    
    public static boolean lambdaplayBeepSound1(final MediaPlayer mediaPlayer, final int i2, final int i3) {
        final String str = BeepManager.TAG;
        Log.w(str, "Failed to beep " + i2 + ", " + i3);
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        return true;
    }
}
