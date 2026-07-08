package com.gu.toolargetool;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import kotlin.jvm.internal.Intrinsics;

import java.util.HashMap;

public final class ActivitySavedStateLogger implements Application.ActivityLifecycleCallbacks {
    private Formatter formatter = null;

    private FragmentSavedStateLogger fragmentLogger = null;

    private boolean isLogging;

    private Logger logger = null;

    private HashMap savedStates = null;

    public ActivitySavedStateLogger(final Formatter formatter, final Logger logger, final boolean z) {
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.formatter = formatter;
        this.logger = logger;
        fragmentLogger = z ? new FragmentSavedStateLogger(formatter, logger) : null;
        savedStates = new HashMap<>();
    }

    public ActivitySavedStateLogger() {

    }


    private void logAndRemoveSavedState(Activity paramActivity) {
        Bundle bundle = (Bundle) this.savedStates.remove(paramActivity);
        if (null != bundle)
            try {
                Formatter formatter = this.formatter;
                String str = formatter.format(paramActivity, bundle);
                Logger logger = this.logger;
                logger.log(str);
            } catch (RuntimeException runtimeException) {
                Logger logger = this.logger;
                logger.logException(runtimeException);
            }
    }

    public boolean isLogging() {
        return this.isLogging;
    }

    public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {
        final String str = "activity";
        Intrinsics.checkNotNullParameter(paramActivity, str);
        boolean bool = paramActivity instanceof FragmentActivity;
        if (bool) {
            FragmentSavedStateLogger fragmentSavedStateLogger = this.fragmentLogger;
            if (null != fragmentSavedStateLogger) {
                FragmentManager fragmentManager = ((FragmentActivity)paramActivity).getSupportFragmentManager();
                fragmentSavedStateLogger = this.fragmentLogger;
                final boolean bool1 = true;
                fragmentManager.registerFragmentLifecycleCallbacks(fragmentSavedStateLogger, bool1);
            }
        }
    }

    public void onActivityDestroyed(Activity paramActivity) {
        Intrinsics.checkNotNullParameter(paramActivity, "activity");
        logAndRemoveSavedState(paramActivity);
    }

    public void onActivityPaused(Activity paramActivity) {
        Intrinsics.checkNotNullParameter(paramActivity, "activity");
    }

    public void onActivityResumed(Activity paramActivity) {
        Intrinsics.checkNotNullParameter(paramActivity, "activity");
    }

    public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {
        Intrinsics.checkNotNullParameter(paramActivity, "activity");
        final String str = "outState";
        Intrinsics.checkNotNullParameter(paramBundle, str);
        boolean bool = this.isLogging;
        if (bool) {
            HashMap<Activity, Bundle> hashMap = this.savedStates;
            hashMap.put(paramActivity, paramBundle);
        }
    }

    public void onActivityStarted(Activity paramActivity) {
        Intrinsics.checkNotNullParameter(paramActivity, "activity");
    }

    public void onActivityStopped(Activity paramActivity) {
        Intrinsics.checkNotNullParameter(paramActivity, "activity");
        logAndRemoveSavedState(paramActivity);
    }

    public void startLogging() {
        final boolean bool = true;
        this.isLogging = bool;
        FragmentSavedStateLogger fragmentSavedStateLogger = this.fragmentLogger;
        if (null != fragmentSavedStateLogger)
            fragmentSavedStateLogger.startLoggingtoolargetool_release();
    }

    public void stopLogging() {
        this.isLogging = false;
        this.savedStates.clear();
        FragmentSavedStateLogger fragmentSavedStateLogger = this.fragmentLogger;
        if (null != fragmentSavedStateLogger)
            fragmentSavedStateLogger.stopLoggingtoolargetool_release();
    }
}
