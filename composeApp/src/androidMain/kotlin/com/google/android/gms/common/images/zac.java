package com.google.android.gms.common.images;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zac implements Runnable {
    final ImageManager zaa;
    private final Uri zab;
    @Nullable
    private final Bitmap zac;
    private final CountDownLatch zad;

    public zac(ImageManager imageManager, @Nullable Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
        this.zaa = imageManager;
        this.zab = uri;
        this.zac = bitmap;
        this.zad = countDownLatch;
    }

    public void run() {
        Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
        ImageManager.ImageReceiver imageReceiver = (ImageManager.ImageReceiver) this.zaa.zai().remove(this.zab);
        if (null != imageReceiver) {
            ArrayList zaa2 = imageReceiver.zac;
            int size = zaa2.size();
            for (int i2 = 0; i2 < size; i2++) {
                zag zag = (zag) zaa2.get(i2);
                Bitmap bitmap = this.zac;
                if (null != bitmap) {
                    zag.zac(this.zaa.zad(), bitmap, false);
                } else {
                    this.zaa.zaj().put(this.zab, Long.valueOf(SystemClock.elapsedRealtime()));
                    ImageManager imageManager = this.zaa;
                    zag.zab(imageManager.zad(), imageManager.zag(), false);
                }
                if (!(zag instanceof zaf)) {
                    this.zaa.zah().remove(zag);
                }
            }
        }
        this.zad.countDown();
        synchronized (ImageManager.zaa) {
            ImageManager.zab.remove(this.zab);
        }
    }
}
