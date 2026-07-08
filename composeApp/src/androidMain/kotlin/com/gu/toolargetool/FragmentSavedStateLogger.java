package com.gu.toolargetool;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import kotlin.jvm.internal.Intrinsics;

import java.util.HashMap;

public final class FragmentSavedStateLogger extends FragmentManager.FragmentLifecycleCallbacks {
    private final Formatter formatter;
    private boolean isLogging;
    private final Logger logger;
    private final HashMap<Fragment, Bundle> savedStates;

    public FragmentSavedStateLogger(final Formatter formatter, final Logger logger) {
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.formatter = formatter;
        this.logger = logger;
        savedStates = new HashMap<>();
        isLogging = true;
    }
    public void onFragmentSaveInstanceState(final FragmentManager fm, final Fragment f2, final Bundle outState) {
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(f2, "f");
        Intrinsics.checkNotNullParameter(outState, "outState");
        if (isLogging) {
            savedStates.put(f2, outState);
        }
    }
    public void onFragmentStopped(final FragmentManager fm, final Fragment f2) {
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(f2, "f");
        this.logAndRemoveSavedState(f2, fm);
    }
    public void onFragmentDestroyed(final FragmentManager fm, final Fragment f2) {
        Intrinsics.checkNotNullParameter(fm, "fm");
        Intrinsics.checkNotNullParameter(f2, "f");
        this.logAndRemoveSavedState(f2, fm);
    }
    private void logAndRemoveSavedState(final Fragment fragment, final FragmentManager fragmentManager) {
        final Bundle remove = savedStates.remove(fragment);
        if (null != remove) {
            try {
                logger.log(formatter.format(fragmentManager, fragment, remove));
            } catch (final RuntimeException e2) {
                logger.logException(e2);
            }
        }
    }
    public void startLoggingtoolargetool_release() {
        isLogging = true;
    }
    public void stopLoggingtoolargetool_release() {
        isLogging = false;
    }
}
