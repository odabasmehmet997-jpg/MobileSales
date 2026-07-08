package com.proje.mobilesales.core;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.RequiresPermission;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.Configuration;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.interfaces.di.ApplicationComponent;
import com.proje.mobilesales.core.interfaces.di.DaggerApplicationComponent;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.receiver.ConnectivityReceiver;
import com.proje.mobilesales.core.receiver.LocationReceiver;
import com.proje.mobilesales.core.service.LocationUpdatesService;
import com.proje.mobilesales.core.utils.LanguageHelper;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import java.io.IOException;
import java.net.SocketException;
public class MobileSales extends Application implements Configuration.Provider {
    public static final String TAG = "MobileSales";
    private static MobileSales mInstance;
    private ApplicationComponent appComponent;
    private Context mContext;
    private Location mCurrentLocation;
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder().setMinimumLoggingLevel(6).build();
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onCreate() {
        super.onCreate();
        Preferences.migrateSensitivePrefs(getApplicationContext());
        Preferences.writeConstantsToPrefsIfNeeded(getApplicationContext());
        Preferences.writeSecretsToPrefsIfNeeded(getApplicationContext());
        this.appComponent = DaggerApplicationComponent.builder().build();
        mInstance = this;
        GoogleAnalytics.getInstance(this).newTracker(R.xml.ga_config);
        Preferences.migrate(this);
        this.mContext = this;
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            public void accept(Throwable obj) {
                try {
                    MobileSales.lambdaonCreate0(obj);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } 
            public Object invoke(Object obj) {

                return obj;
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(new LocationReceiver() {  
            void C24441() {
            } 
            public void onReceive(Context context, Intent intent) {
                MobileSales.this.mCurrentLocation = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
                Log.i("AppLocationReceiver", "New location: " + MobileSales.this.mCurrentLocation);
            }
        }, new IntentFilter(LocationUpdatesService.ACTION_BROADCAST));
    }
    public static  void lambdaonCreate0(Throwable th) throws Exception {
        if (th instanceof UndeliverableException) {
            th = th.getCause();
        }
        if ((th instanceof IOException) || (th instanceof SocketException) || (th instanceof InterruptedException)) {
            return;
        }
        if ((th instanceof NullPointerException) || (th instanceof IllegalArgumentException)) {
            Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        } else if (th instanceof IllegalStateException) {
            Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        } else {
            Log.e(TAG, "Undeliverable exception received", th);
        }
    } 
    class C24441 extends LocationReceiver {
        C24441() {
        } 
        public void onReceive(Context context, Intent intent) {
            MobileSales.this.mCurrentLocation = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
            Log.i("AppLocationReceiver", "New location: " + MobileSales.this.mCurrentLocation);
        }
    }
    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener connectivityReceiverListener) {
        ConnectivityReceiver.connectivityReceiverListener = connectivityReceiverListener;
    }
    public static synchronized MobileSales getInstance() {
        MobileSales mobileSales;
        synchronized (MobileSales.class) {
            mobileSales = mInstance;
        }
        return mobileSales;
    } 
    public void onConfigurationChanged(android.content.res.Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(TAG, "onConfigurationChanged: " + configuration.locale.getLanguage());
    } 
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageHelper.onAttach(context, LanguageHelper.getLanguage(context)));
    } 
    public void onTerminate() {
        Log.d(TAG, "onTerminate: app terminated");
        super.onTerminate();
    } 
    public void onLowMemory() {
        Log.d(TAG, "onTerminate: app low memory");
        super.onLowMemory();
    }
    public Context getmContext() {
        return this.mContext;
    }
    public ApplicationComponent getAppComponent() {
        return this.appComponent;
    }
    public Location getCurrentLocation() {
        String sb = "Location request from " +
                (Looper.myLooper() == Looper.getMainLooper() ? "UI thread" : "Background");
        Log.d(TAG, sb);
        return this.mCurrentLocation;
    }
}
