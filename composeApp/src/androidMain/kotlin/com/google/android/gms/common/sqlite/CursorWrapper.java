package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zza;

    @KeepForSdk
    public void fillWindow(int i2, @NonNull CursorWindow cursorWindow) {
        this.zza.fillWindow(i2, cursorWindow);
    }

    
    @KeepForSdk
    @Nullable
    public CursorWindow getWindow() {
        return this.zza.getWindow();
    }

    @NonNull
    public final Cursor getWrappedCursor() {
        return this.zza;
    }

    
    public final boolean onMove(int i2, int i3) {
        return this.zza.onMove(i2, i3);
    }
}
