package com.proje.mobilesales.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import com.proje.mobilesales.core.service.LocationUpdatesService;

public class LocationReceiver extends BroadcastReceiver {
    private Location mLocation;
    public void onReceive(Context context, Intent intent) {
        this.mLocation = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
        Log.i("LocationReceiver", "New location: " + this.mLocation);
    }
}
