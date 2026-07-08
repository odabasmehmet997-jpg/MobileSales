package com.proje.mobilesales.core.service;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.features.gpsinfo.model.database.GpsInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class GpsTrackerNotUsed extends Service implements LocationListener {
    private static final String TAG = "GpsTracker";
    private static final int TWO_MINUTES = 120000;
    public static double latitude;
    public static double longitude;
    private BaseErp baseErp;
    private Location lastLocation;
    protected LocationManager locationManager;
    private Location prevLocation;
    private boolean saveGpsLog;
    ScheduledExecutorService scheduledExecutorService;
    private final long _MINIMUM_TIME = 5;
    private long locationBetweenTime = 0;
    private long sendToDataPeriod = 0;
    private final float minDistance = 20.0f;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;
    boolean sendData = false;
    final Runnable sendGpsRunnable = new Runnable() { // from class: com.proje.mobilesales.core.service.GpsTrackerNotUsed.1
        @Override // java.lang.Runnable
        public void run() {
            GpsTrackerNotUsed gpsTrackerNotUsed = GpsTrackerNotUsed.this;
            gpsTrackerNotUsed.sendData = true;
            if (Connectivity.isConnected(gpsTrackerNotUsed.getApplicationContext())) {
                GpsTrackerNotUsed.this.getBaseErp().sendGpsLog();
            }
            GpsTrackerNotUsed gpsTrackerNotUsed2 = GpsTrackerNotUsed.this;
            gpsTrackerNotUsed2.sendData = !gpsTrackerNotUsed2.sendData;
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public BaseErp getBaseErp() {
        BaseErp baseErp = this.baseErp;
        return baseErp == null ? ErpCreator.getInstance().getmBaseErp() : baseErp;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Started gps service");
        try {
            try {
                if (getBaseErp() == null || MobileSales.getInstance().getmContext() == null) {
                    stopSelf();
                    return;
                }
                TimeUnit timeUnit = TimeUnit.MINUTES;
                this.locationBetweenTime = timeUnit.toMillis(getBaseErp().getTimeBetweenGpsLocation());
                this.sendToDataPeriod = timeUnit.toMillis(getBaseErp().getTimeBetweenGpsData());
                this.saveGpsLog = getBaseErp().useGpsLog();
                if (this.locationBetweenTime < timeUnit.toMillis(5L)) {
                    this.locationBetweenTime = timeUnit.toMillis(5L);
                }
                if (this.sendToDataPeriod < timeUnit.toMillis(5L)) {
                    this.sendToDataPeriod = timeUnit.toMillis(5L);
                }
                this.locationBetweenTime = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
                this.scheduledExecutorService = newScheduledThreadPool;
                newScheduledThreadPool.scheduleAtFixedRate(this.sendGpsRunnable, 0L, this.sendToDataPeriod, TimeUnit.MILLISECONDS);
                getLocation();
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

    public Location getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(FirebaseAnalytics.Param.LOCATION);
            this.locationManager = locationManager;
            this.isGPSEnabled = locationManager.isProviderEnabled("gps");
            boolean isProviderEnabled = this.locationManager.isProviderEnabled("network");
            this.isNetworkEnabled = isProviderEnabled;
            if (this.isGPSEnabled || isProviderEnabled) {
                this.canGetLocation = true;
                Criteria gpsCriteria = getGpsCriteria();
                if (this.isGPSEnabled && this.lastLocation == null) {
                    try {
                        this.locationManager.requestLocationUpdates(0L, 20.0f, gpsCriteria, this, null);
                        Log.d("GPS Enabled", "GPS Enabled");
                        LocationManager locationManager2 = this.locationManager;
                        if (locationManager2 != null) {
                            Location lastKnownLocation = locationManager2.getLastKnownLocation("gps");
                            this.lastLocation = lastKnownLocation;
                            if (lastKnownLocation != null) {
                                latitude = lastKnownLocation.getLatitude();
                                longitude = this.lastLocation.getLongitude();
                            }
                        }
                    } catch (SecurityException unused) {
                    }
                }
                if (this.isNetworkEnabled) {
                    try {
                        this.locationManager.requestLocationUpdates(0L, 20.0f, gpsCriteria, this, null);
                        Log.d("Network", "Network");
                        LocationManager locationManager3 = this.locationManager;
                        if (locationManager3 != null) {
                            Location lastKnownLocation2 = locationManager3.getLastKnownLocation("network");
                            this.lastLocation = lastKnownLocation2;
                            if (lastKnownLocation2 != null) {
                                latitude = lastKnownLocation2.getLatitude();
                                longitude = this.lastLocation.getLongitude();
                            }
                        }
                    } catch (SecurityException unused2) {
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this.lastLocation;
    }

    public Criteria getGpsCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setPowerRequirement(3);
        criteria.setAltitudeRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setBearingRequired(false);
        criteria.setHorizontalAccuracy(3);
        criteria.setVerticalAccuracy(3);
        return criteria;
    }

    public void stopUsingGPS() {
        LocationManager locationManager = this.locationManager;
        if (locationManager != null) {
            try {
                locationManager.removeUpdates(this);
            } catch (SecurityException unused) {
            }
        }
    }

    public double getLatitude() {
        Location location = this.lastLocation;
        if (location != null) {
            latitude = location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude() {
        Location location = this.lastLocation;
        if (location != null) {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (isBetterLocation(location, this.lastLocation)) {
            Log.d(TAG, "onLocationChanged() called with: loc = [" + location + "]");
            this.lastLocation = location;
            latitude = location.getLatitude();
            longitude = this.lastLocation.getLongitude();
            Location location2 = this.prevLocation;
            if ((location2 == null || DateAndTimeUtils.getTimeSpanTotalSeconds(location2.getTime(), this.lastLocation.getTime()) <= TimeUnit.MILLISECONDS.toSeconds(this.sendToDataPeriod)) && this.prevLocation != null) {
                return;
            }
            if (!this.sendData || this.saveGpsLog) {
                GpsInfo gpsInfo = new GpsInfo();
                gpsInfo.latitude = this.lastLocation.getLatitude();
                gpsInfo.longtitude = this.lastLocation.getLongitude();
                if (this.prevLocation != null) {
                    gpsInfo.distance = this.lastLocation.distanceTo(r0);
                    gpsInfo.timeSpan = DateAndTimeUtils.getTimeSpanTotalSeconds(this.prevLocation.getTime(), this.lastLocation.getTime());
                } else {
                    gpsInfo.distance = 0.0d;
                    gpsInfo.timeSpan = 0.0d;
                }
                gpsInfo.speed = getSpeed(this.lastLocation, gpsInfo.distance, gpsInfo.timeSpan);
                gpsInfo.gpsDate = DateAndTimeUtils.getDateFull(this.lastLocation.getTime());
                gpsInfo.transfer = 0;
                final ArrayList arrayList = new ArrayList();
                arrayList.add(gpsInfo);
                new Thread(new Runnable() { // from class: com.proje.mobilesales.core.service.GpsTrackerNotUsedExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public void run() {
                        GpsTrackerNotUsed.this.lambdaonLocationChanged0(arrayList);
                    }
                }).start();
            }
            this.prevLocation = this.lastLocation;
        }
    }

    
    public void lambdaonLocationChanged0(List list) {
        getBaseErp().getLogoSqlBriteDatabase().insert(list, false);
    }

    private double getSpeed(Location location, double d2, double d3) {
        return location.hasSpeed() ? location.getSpeed() : d2 / d3;
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        ContextUtils.checkGpsConnection();
        Log.d(TAG, "onProviderDisabled() called with: provider = [" + str + "]");
        latitude = 0.0d;
        longitude = 0.0d;
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
        Log.d(TAG, "onProviderEnabled() called with: provider = [" + str + "]");
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i2, Bundle bundle) {
        Log.d(TAG, "onStatusChanged() called with: provider = [" + str + "], status = [" + i2 + "], extras = [" + bundle + "]");
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(TAG, "Stopping gps service");
        stopUsingGPS();
        ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        Log.i(TAG, "onStartCommand: called");
        return super.onStartCommand(intent, i2, i3);
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
