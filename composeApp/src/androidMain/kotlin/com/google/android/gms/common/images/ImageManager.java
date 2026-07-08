package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import com.google.android.gms.internal.base.zau;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @param zad access modifiers changed from: private
 * @param zae access modifiers changed from: private
 * @param zaf access modifiers changed from: private
 * @param zag access modifiers changed from: private
 * @param zah access modifiers changed from: private
 * @param zai access modifiers changed from: private
 * @param zaj access modifiers changed from: private
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public record ImageManager(Context zad, Handler zae, ExecutorService zaf, zam zag, Map zah, Map zai, Map zaj) {
    
    public static final Object zaa = new Object();
    
    public static final HashSet zab = new HashSet();

    @KeepName
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    private final class ImageReceiver extends ResultReceiver {
        private final Uri zab;
        
        public final ArrayList zac = new ArrayList();

        ImageReceiver(Uri uri) {
            super(new zau(Looper.getMainLooper()));
            this.zab = uri;
        }

        public void onReceiveResult(int i2, Bundle bundle) {
            ImageManager imageManager = ImageManager.this;
            imageManager.zaf.execute(new zaa(imageManager, this.zab, bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }

        public void zab(zag zag) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zac.add(zag);
        }

        public void zac(zag zag) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zac.remove(zag);
        }

        public void zad() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.setPackage("com.google.android.gms");
            intent.putExtra("com.google.android.gms.extras.uri", this.zab);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.zad.sendBroadcast(intent);
        }
    }

    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public interface OnImageLoadedListener {
        void onImageLoaded(@NonNull Uri uri, @Nullable Drawable drawable, boolean z);
    }
}
