package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * @param zaa synthetic
 * @param zab synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
record zae(Context zaa, Intent zab) implements View.OnClickListener {

    public void onClick(View view) {
        try {
            this.zaa.startActivity(this.zab);
        } catch (ActivityNotFoundException e2) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e2);
        }
    }
}
