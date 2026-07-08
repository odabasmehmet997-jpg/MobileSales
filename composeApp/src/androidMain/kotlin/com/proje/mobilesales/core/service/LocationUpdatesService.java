package com.proje.mobilesales.core.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.LanguageHelper;
import com.proje.mobilesales.features.gpsinfo.model.database.GpsInfo;
import com.proje.mobilesales.features.notification.view.NotificationActivity;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LocationUpdatesService extends Service {
    public static final String ACTION_BROADCAST = "com.proje.mobilesales.core.service.broadcast";
    public static final String EXTRA_LOCATION = "com.proje.mobilesales.core.service.location";
    private static final String EXTRA_STARTED_FROM_NOTIFICATION = "com.proje.mobilesales.core.service.started_from_notification";
    public static final String LOCATION_UPDATE_CHANNEL_ID = "locationUpdate_channel";
    public static final int LOCATION_UPDATE_NOTIFICATION_ID = 12345678;
    private static final String PACKAGE_NAME = "com.proje.mobilesales.core.service";
    private static final String TAG = "LocationUpdatesService";
    private static final int TWO_MINUTES = 120000;
    private BaseErp baseErp;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;
    private NotificationManager mNotificationManager;
    private Location mPreviousLocation;
    private Handler mServiceHandler;
    boolean saveGpsLog;
    ScheduledExecutorService scheduledExecutorService;
    private final long _MINIMUM_TIME = 5;
    private final IBinder mBinder = new LocalBinder();
    private boolean mChangingConfiguration = false;
    boolean sendData = false;
    private long sendToDataPeriod = 0;
    private int suspiciousActivityDistance = 0;
    private boolean requestingLocationUpdates = false;
    final Runnable sendGpsRunnable = new Runnable() {
        public void run() {
            LocationUpdatesService.this.lambdanew0();
        }
    };

    public BaseErp getBaseErp() {
        BaseErp baseErp = this.baseErp;
        return baseErp == null ? ErpCreator.getInstance().getmBaseErp() : baseErp;
    }
    public void onCreate() {
        this.baseErp = ErpCreator.getInstance().getmBaseErp();
        try {
            try {
                if (getBaseErp() == null || MobileSales.getInstance().getmContext() == null) {
                    stopSelf();
                    return;
                }
                this.suspiciousActivityDistance = getBaseErp().getSuspiciousActivityDistance();
                TimeUnit timeUnit = TimeUnit.MINUTES;
                this.sendToDataPeriod = timeUnit.toMillis(getBaseErp().getTimeBetweenGpsData());
                this.saveGpsLog = getBaseErp().useGpsLog();
                if (this.sendToDataPeriod < timeUnit.toMillis(5L)) {
                    this.sendToDataPeriod = timeUnit.toMillis(5L);
                }
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
                this.scheduledExecutorService = newScheduledThreadPool;
                newScheduledThreadPool.scheduleAtFixedRate(this.sendGpsRunnable, 0L, this.sendToDataPeriod, TimeUnit.MILLISECONDS);
                this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                this.mLocationCallback = new LocationCallback() { // from class: com.proje.mobilesales.core.service.LocationUpdatesService.1
                    @Override // com.google.android.gms.location.LocationCallback
                    public void onLocationResult(@NonNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationUpdatesService.this.onNewLocation(locationResult.getLastLocation());
                    }
                };
                createLocationRequest();
                getLastLocation();
                String str = TAG;
                HandlerThread handlerThread = new HandlerThread(str);
                handlerThread.start();
                this.mServiceHandler = new Handler(handlerThread.getLooper());
                this.mNotificationManager = (NotificationManager) getSystemService("notification");
                NotificationChannel notificationChannel = new NotificationChannel(LOCATION_UPDATE_CHANNEL_ID, getString(R.string.str_mobile_sales_notification_channel), 1);
                notificationChannel.setShowBadge(false);
                notificationChannel.setDescription(getString(R.string.str_mobile_sales_notification_channel));
                this.mNotificationManager.createNotificationChannel(notificationChannel);
                if (this.mChangingConfiguration) {
                    return;
                }
                Log.i(str, "Starting foreground service");
                startForeground(LOCATION_UPDATE_NOTIFICATION_ID, getNotification());
            } catch (Exception e2) {
                Log.e(TAG, "onCreateStopSelf : ", e2);
                stopSelf();
            }
        } catch (Exception e3) {
            Log.e(TAG, "onCreate: ", e3);
            ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
            if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
                return;
            }
            this.scheduledExecutorService.shutdownNow();
        }
    }

    
    public void lambdanew0() {
        this.sendData = true;
        if (Connectivity.isConnected(getApplicationContext())) {
            try {
                if (this.saveGpsLog) {
                    getBaseErp().sendGpsLog();
                }
            } catch (Exception e2) {
                Log.d(TAG, "Send GPS Error", e2);
            }
        }
        this.sendData = !this.sendData;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Log.i(TAG, "Service started");
        if (!intent.getBooleanExtra(EXTRA_STARTED_FROM_NOTIFICATION, false)) {
            return 2;
        }
        removeLocationUpdates();
        stopSelf();
        return 2;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mChangingConfiguration = true;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "in onBind()");
        stopForeground(1);
        this.mChangingConfiguration = false;
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        Log.i(TAG, "in onRebind()");
        stopForeground(1);
        this.mChangingConfiguration = false;
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "Last client unbound from service");
        return true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Handler handler = this.mServiceHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Log.d(TAG, "Location Updates Service Destroyed");
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        stopSelf();
    }

    public void requestLocationUpdates() {
        Log.i(TAG, "Requesting location updates");
        startService(new Intent(getApplicationContext(), LocationUpdatesService.class));
        try {
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
                this.requestingLocationUpdates = true;
            }
        } catch (SecurityException e2) {
            this.requestingLocationUpdates = false;
            Log.e(TAG, "Lost location permission. Could not request updates. " + e2);
        }
    }

    public void removeLocationUpdates() {
        Log.i(TAG, "Removing location updates");
        try {
            this.mFusedLocationClient.removeLocationUpdates(this.mLocationCallback);
            stopSelf();
        } catch (SecurityException e2) {
            Log.e(TAG, "Lost location permission. Could not remove updates. " + e2);
        }
    }

    private Notification getNotification() {
        Intent intent = new Intent(this, LocationUpdatesService.class);
        String string = getString(R.string.str_tap_to_open_app);
        intent.putExtra(EXTRA_STARTED_FROM_NOTIFICATION, true);
        return new NotificationCompat.Builder(this, LOCATION_UPDATE_CHANNEL_ID).setContentText(DateFormat.getDateTimeInstance().format(new Date())).setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, NotificationActivity.class), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL)).setContentTitle(getString(R.string.str_location_info_updated)).setOngoing(true).setPriority(-2).setVisibility(1).setSmallIcon(R.drawable.ic_stat_ms).setColor(getResources().getColor(R.color.colorAccent)).setTicker(string).setBadgeIconType(0).setNumber(0).setWhen(System.currentTimeMillis()).build();
    }

    private void getLastLocation() {
        try {
            if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 && ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener() { // from class: com.proje.mobilesales.core.service.LocationUpdatesServiceExternalSyntheticLambda1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(Task task) {
                        LocationUpdatesService.this.lambdagetLastLocation1(task);
                    }
                });
            }
        } catch (SecurityException e2) {
            Log.e(TAG, "Lost location permission." + e2);
        }
    }

    
    public void lambdagetLastLocation1(Task task) {
        if (task.isSuccessful()) {
            this.mLocation = (Location) task.getResult();
        } else {
            Log.w(TAG, "Failed to get location.");
        }
    }

    
    public void onNewLocation(Location location) {
        String str = TAG;
        Log.i(str, "New location: " + location);
        if (isBetterLocation(location, this.mLocation)) {
            Log.i(str, "Better New location: " + location);
            this.mLocation = location;
            saveGpsInfo(location);
            Intent intent = new Intent(ACTION_BROADCAST);
            intent.putExtra(EXTRA_LOCATION, location);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            if (serviceIsRunningInForeground(this)) {
                this.mNotificationManager.notify(LOCATION_UPDATE_NOTIFICATION_ID, getNotification());
            }
        }
    }

    private void saveGpsInfo(Location location) {
        Location location2 = this.mPreviousLocation;
        if ((location2 == null || DateAndTimeUtils.getTimeSpanTotalSeconds(location2.getTime(), location.getTime()) <= TimeUnit.MILLISECONDS.toSeconds(this.sendToDataPeriod)) && this.mPreviousLocation != null) {
            return;
        }
        if (this.saveGpsLog) {
            GpsInfo gpsInfo = new GpsInfo();
            gpsInfo.latitude = location.getLatitude();
            gpsInfo.longtitude = location.getLongitude();
            if (this.mPreviousLocation != null) {
                gpsInfo.distance = location.distanceTo(r1);
                gpsInfo.timeSpan = DateAndTimeUtils.getTimeSpanTotalSeconds(this.mPreviousLocation.getTime(), location.getTime());
            } else {
                gpsInfo.distance = 0.0d;
                gpsInfo.timeSpan = 0.0d;
            }
            gpsInfo.speed = getSpeed(location, gpsInfo.distance, gpsInfo.timeSpan);
            gpsInfo.gpsDate = DateAndTimeUtils.getDateFull(location.getTime());
            gpsInfo.transfer = 0;
            final ArrayList arrayList = new ArrayList();
            arrayList.add(gpsInfo);
            new Thread(new Runnable() { // from class: com.proje.mobilesales.core.service.LocationUpdatesServiceExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public void run() {
                    LocationUpdatesService.this.lambdasaveGpsInfo2(arrayList);
                }
            }).start();
        }
        this.mPreviousLocation = location;
    }

    
    public void lambdasaveGpsInfo2(List list) {
        try {
            getBaseErp().getLogoSqlBriteDatabase().insert(list, false);
        } catch (Exception e2) {
            Log.d(TAG, "GPS Insert Error", e2);
        }
    }

    private double getSpeed(Location location, double d2, double d3) {
        return location.hasSpeed() ? location.getSpeed() : d2 / d3;
    }

    private void createLocationRequest() {
        LocationRequest create = LocationRequest.create();
        this.mLocationRequest = create;
        create.setInterval(0L);
        this.mLocationRequest.setFastestInterval(0L);
        this.mLocationRequest.setPriority(100);
        LocationRequest locationRequest = this.mLocationRequest;
        int i2 = this.suspiciousActivityDistance;
        locationRequest.setSmallestDisplacement((i2 == 0 || i2 > 20) ? 20.0f : i2);
    }

    @Override // android.app.Service, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageHelper.onAttach(context));
    }

    public boolean isRequestingLocationUpdates() {
        return this.requestingLocationUpdates;
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public LocationUpdatesService getService() {
            return LocationUpdatesService.this;
        }
    }

    public boolean serviceIsRunningInForeground(Context context) {
        List<ActivityManager.RunningServiceInfo> runningServices;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)) == null) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            if (getClass().getName().equals(runningServiceInfo.service.getClassName()) && runningServiceInfo.foreground) {
                return true;
            }
        }
        return false;
    }

    protected boolean isBetterLocation(Location location, Location location2) {
        if (location2 == null) {
            return true;
        }
        long time = location.getTime() - location2.getTime();
        boolean z = time > 120000;
        boolean z2 = time < -120000;
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = accuracy > 200;
        boolean isSameProvider = isSameProvider(location.getProvider(), location2.getProvider());
        if (z5) {
            return true;
        }
        if (!z3 || z4) {
            return z3 && !z6 && isSameProvider;
        }
        return true;
    }

    private boolean isSameProvider(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }
}
