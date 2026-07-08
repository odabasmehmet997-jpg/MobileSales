package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class SupportMapFragment extends Fragment {
    private final zzaw zza = new zzaw(this);
    public void onActivityCreated(final Bundle bundle) {
        final ClassLoader classLoader = SupportMapFragment.class.getClassLoader();
        if (!(null == bundle || null == classLoader)) {
            bundle.setClassLoader(classLoader);
        }
        super.onActivityCreated(bundle);
    }
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        zzaw.zza(zza, activity);
    }
    public void onCreate(final Bundle bundle) {
        final StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            super.onCreate(bundle);
            zza.onCreate(bundle);
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
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
    public void onInflate(final Activity activity, final AttributeSet attributeSet, final Bundle bundle) {
        final StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            super.onInflate(activity, attributeSet, bundle);
            zzaw.zza(zza, activity);
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
    public void onSaveInstanceState(final Bundle bundle) {
        final ClassLoader classLoader = SupportMapFragment.class.getClassLoader();
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
    public void setArguments(final Bundle bundle) {
        super.setArguments(bundle);
    }
}
