package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.*;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.Preconditions;

public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    @VisibleForTesting
    protected int zaa;

    @NonNull
    public static Intent zaa(@NonNull final Context context, @NonNull final PendingIntent pendingIntent, final int i2, final boolean z) {
        final Intent intent = new Intent(context, GoogleApiActivity.class);
        intent.putExtra("pending_intent", pendingIntent);
        intent.putExtra("failing_client_id", i2);
        intent.putExtra("notify_manager", z);
        return intent;
    }

    private final void zab() {
        final Bundle extras = this.getIntent().getExtras();
        if (null == extras) {
            Log.e("GoogleApiActivity", "Activity started without extras");
            this.finish();
            return;
        }
        final PendingIntent pendingIntent = (PendingIntent) extras.get("pending_intent");
        final Integer num = (Integer) extras.get("error_code");
        if (null == pendingIntent && null == num) {
            Log.e("GoogleApiActivity", "Activity started without resolution");
            this.finish();
        } else if (null != pendingIntent) {
            try {
                this.startIntentSenderForResult(pendingIntent.getIntentSender(), 1, (Intent) null, 0, 0, 0);
                zaa = 1;
            } catch (final ActivityNotFoundException e2) {
                if (extras.getBoolean("notify_manager", true)) {
                    GoogleApiManager.zak(this).zax(new ConnectionResult(22, null), this.getIntent().getIntExtra("failing_client_id", -1));
                } else {
                    String str = "Activity not found while launching " + pendingIntent + ".";
                    if (Build.FINGERPRINT.contains("generic")) {
                        str = str + " This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.";
                    }
                    Log.e("GoogleApiActivity", str, e2);
                }
                zaa = 1;
                this.finish();
            } catch (final IntentSender.SendIntentException e3) {
                Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e3);
                this.finish();
            }
        } else {
            GoogleApiAvailability.getInstance().showErrorDialogFragment(this, Preconditions.checkNotNull(num).intValue(), 2, this);
            zaa = 1;
        }
    }

    
    public final void onActivityResult(final int i2, final int i3, @NonNull final Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (1 == i2) {
            final boolean booleanExtra = this.getIntent().getBooleanExtra("notify_manager", true);
            zaa = 0;
            this.setResult(i3, intent);
            if (booleanExtra) {
                final GoogleApiManager zak = GoogleApiManager.zak(this);
                if (-1 == i3) {
                    zak.zay();
                } else if (0 == i3) {
                    zak.zax(new ConnectionResult(13, null), this.getIntent().getIntExtra("failing_client_id", -1));
                }
            }
        } else if (2 == i2) {
            zaa = 0;
            this.setResult(i3, intent);
        }
        this.finish();
    }

    public final void onCancel(@NonNull final DialogInterface dialogInterface) {
        zaa = 0;
        this.setResult(0);
        this.finish();
    }

    
    public final void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
        if (null != bundle) {
            zaa = bundle.getInt("resolution");
        }
        if (1 != this.zaa) {
            this.zab();
        }
    }

    
    public final void onSaveInstanceState(@NonNull final Bundle bundle) {
        bundle.putInt("resolution", zaa);
        super.onSaveInstanceState(bundle);
    }
}
