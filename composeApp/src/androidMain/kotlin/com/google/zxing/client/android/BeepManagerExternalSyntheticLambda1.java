package com.google.zxing.client.android;

import android.media.MediaPlayer;

public final class BeepManagerExternalSyntheticLambda1 implements MediaPlayer.OnErrorListener {
    public boolean onError(final MediaPlayer mediaPlayer, final int i2, final int i3) {
        return BeepManager.lambdaplayBeepSound1(mediaPlayer, i2, i3);
    }
}
