package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzb extends Fragment implements LifecycleFragment {
    private static final WeakHashMap zza = new WeakHashMap();
    private final Map zzb = Collections.synchronizedMap(new ArrayMap());
    
    public int zzc;
    
    @Nullable
    public Bundle zzd;

    public static zzb zzc(final Activity activity) {
        final zzb zzb2;
        final WeakHashMap weakHashMap = zzb.zza;
        final WeakReference weakReference = (WeakReference) weakHashMap.get(activity);
        if (null != weakReference && null != (zzb2 = (zzb) weakReference.get())) {
            return zzb2;
        }
        try {
            zzb zzb3 = (zzb) activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
            if (null == zzb3 || zzb3.isRemoving()) {
                zzb3 = new zzb();
                activity.getFragmentManager().beginTransaction().add(zzb3, "LifecycleFragmentImpl").commitAllowingStateLoss();
            }
            weakHashMap.put(activity, new WeakReference(zzb3));
            return zzb3;
        } catch (final ClassCastException e2) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", e2);
        }
    }

    public void addCallback(final String str, @NonNull final LifecycleCallback lifecycleCallback) {
        if (!zzb.containsKey(str)) {
            zzb.put(str, lifecycleCallback);
            if (0 < this.zzc) {
                new zzi(Looper.getMainLooper()).post(new zza(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("LifecycleCallback with tag " + str + " already added to this fragment.");
    }

    public void dump(final String str, @Nullable final FileDescriptor fileDescriptor, final PrintWriter printWriter, @Nullable final String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (final LifecycleCallback dump : zzb.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Nullable
    public <T extends LifecycleCallback> T getCallbackOrNull(final String str, final Class<T> cls) {
        return cls.cast(zzb.get(str));
    }

    @Nullable
    public Activity getLifecycleActivity() {
        return this.getActivity();
    }

    public void onActivityResult(final int i2, final int i3, @Nullable final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        for (final LifecycleCallback onActivityResult : zzb.values()) {
            onActivityResult.onActivityResult(i2, i3, intent);
        }
    }

    public void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
        zzc = 1;
        zzd = bundle;
        for (final Map.Entry entry : zzb.entrySet()) {
            ((LifecycleCallback) entry.getValue()).onCreate(null != bundle ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        zzc = 5;
        for (final LifecycleCallback onDestroy : zzb.values()) {
            onDestroy.onDestroy();
        }
    }

    public void onResume() {
        super.onResume();
        zzc = 3;
        for (final LifecycleCallback onResume : zzb.values()) {
            onResume.onResume();
        }
    }

    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (null != bundle) {
            for (final Map.Entry entry : zzb.entrySet()) {
                final Bundle bundle2 = new Bundle();
                ((LifecycleCallback) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public void onStart() {
        super.onStart();
        zzc = 2;
        for (final LifecycleCallback onStart : zzb.values()) {
            onStart.onStart();
        }
    }

    public void onStop() {
        super.onStop();
        zzc = 4;
        for (final LifecycleCallback onStop : zzb.values()) {
            onStop.onStop();
        }
    }
}
