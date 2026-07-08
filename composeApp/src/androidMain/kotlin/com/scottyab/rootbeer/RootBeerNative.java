package com.scottyab.rootbeer;

import com.scottyab.rootbeer.util.QLog;

public class RootBeerNative {
    private static boolean libraryLoaded;
    static {
        try {
            System.loadLibrary ("toolChecker");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e2) {
            QLog.m583e (e2);
        }
    }
    public native int checkForRoot(Object[] objArr);
    public native int setLogDebugMessages(boolean z);
    public boolean wasNativeLibraryLoaded() {
        return libraryLoaded;
    }
}
