package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzd extends Fragment implements LifecycleFragment {
    private static final WeakHashMap zza = new WeakHashMap();
    private final Map zzb = Collections.synchronizedMap(new ArrayMap());
    
    public int zzc;
    
    @Nullable
    public Bundle zzd;

    public static zzd zzc(final FragmentActivity fragmentActivity) {
        final zzd zzd2;
        final WeakHashMap weakHashMap = zzd.zza;
        final WeakReference weakReference = (WeakReference) weakHashMap.get(fragmentActivity);
        if (null != weakReference && null != (zzd2 = (zzd) weakReference.get())) {
            return zzd2;
        }
        try {
            zzd zzd3 = (zzd) fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
            if (null == zzd3 || zzd3.isRemoving()) {
                zzd3 = new zzd();
                fragmentActivity.getSupportFragmentManager().beginTransaction().add(zzd3, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
            }
            weakHashMap.put(fragmentActivity, new WeakReference(zzd3));
            return zzd3;
        } catch (final ClassCastException e2) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", e2);
        }
    }

    public void addCallback(final String str, @NonNull final LifecycleCallback lifecycleCallback) {
        if (!zzb.containsKey(str)) {
            zzb.put(str, lifecycleCallback);
            if (0 < this.zzc) {
                new zzi(Looper.getMainLooper()).post(new zzc(this, lifecycleCallback, str));
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
