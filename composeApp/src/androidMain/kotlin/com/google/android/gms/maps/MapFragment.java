package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public class MapFragment extends Fragment {
    private final zzaf zza = new zzaf(this);

    public void getMapAsync(@NonNull final OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
        Preconditions.checkNotNull(onMapReadyCallback, "callback must not be null.");
        zza.zzb(onMapReadyCallback);
    }

    public void onActivityCreated(@Nullable final Bundle bundle) {
        final ClassLoader classLoader = MapFragment.class.getClassLoader();
        if (!(null == bundle || null == classLoader)) {
            bundle.setClassLoader(classLoader);
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(@NonNull final Activity activity) {
        super.onAttach(activity);
        zzaf.zza(zza, activity);
    }

    public void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
        zza.onCreate(bundle);
    }

    @NonNull
    public View onCreateView(@NonNull final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, @Nullable final Bundle bundle) {
        final View onCreateView = zza.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        return onCreateView;
    }

    public void onDestroy() {
        zza.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        zza.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(@NonNull final Activity activity, @NonNull final AttributeSet attributeSet, @Nullable final Bundle bundle) {
        final StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            zzaf.zza(zza, activity);
            final GoogleMapOptions createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
            final Bundle bundle2 = new Bundle();
            bundle2.putParcelable("MapOptions", createFromAttributes);
            zza.onInflate(activity, bundle2, bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onLowMemory() {
        zza.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        zza.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        zza.onResume();
    }

    public void onSaveInstanceState(@NonNull final Bundle bundle) {
        final ClassLoader classLoader = MapFragment.class.getClassLoader();
        if (!(null == bundle || null == classLoader)) {
            bundle.setClassLoader(classLoader);
        }
        super.onSaveInstanceState(bundle);
        zza.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        zza.onStart();
    }

    public void onStop() {
        zza.onStop();
        super.onStop();
    }

    public void setArguments(@Nullable final Bundle bundle) {
        super.setArguments(bundle);
    }
}
