package com.proje.mobilesales.core;

import android.content.Context;
import android.database.Cursor;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import com.proje.mobilesales.core.design.ErpCreator;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class MyCursorLoader extends AsyncTaskLoader<Cursor> {
    private String filter;
    private CancellationSignal mCancellationSignal;
    private Cursor mCursor;
    private final Loader<Cursor>.ForceLoadContentObserver mObserver;
    private String sql;
    public MyCursorLoader(Context context) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
    }
    public MyCursorLoader(Context context, String str) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
        this.sql = str;
    }
    public MyCursorLoader(Context context, String str, String str2) {
        super(context);
        this.mObserver = new Loader.ForceLoadContentObserver();
        this.sql = str;
        this.filter = str2;
    }
    public Cursor loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            this.mCancellationSignal = new CancellationSignal();
        }
        try {
            Cursor query = ErpCreator.getInstance().getmBaseErp().getLogoSqlBriteDatabase().query(this.sql);
            if (query != null) {
                try {
                    query.getCount();
                    query.registerContentObserver(this.mObserver);
                } catch (RuntimeException e2) {
                    query.close();
                    throw e2;
                }
            }
            synchronized (this) {
                this.mCancellationSignal = null;
            }
            return query;
        } catch (Throwable th) {
            synchronized (this) {
                this.mCancellationSignal = null;
                throw th;
            }
        }
    }
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            try {
                CancellationSignal cancellationSignal = this.mCancellationSignal;
                if (cancellationSignal != null) {
                    cancellationSignal.cancel();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public void deliverResult(Cursor cursor) {
        if (isReset()) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor cursor2 = this.mCursor;
        this.mCursor = cursor;
        if (isStarted()) {
            super.deliverResult(cursor);
        }
        if (cursor2 == null || cursor2.equals(cursor) || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }
    protected void onStartLoading() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.mCursor == null) {
            forceLoad();
        }
    }
    protected void onStopLoading() {
        cancelLoad();
    }
    public void onCanceled(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }
    protected void onReset() {
        super.onReset();
        onStopLoading();
        Cursor cursor = this.mCursor;
        if (cursor != null && !cursor.isClosed()) {
            this.mCursor.close();
        }
        this.mCursor = null;
    }
    public void setFilter(String str) {
        this.filter = str;
    }
    public String getFilter() {
        return this.filter;
    }
    public void setSql(String str) {
        this.sql = str;
    }
    public String getSql() {
        return this.sql;
    }
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mSql=");
        printWriter.println(this.sql);
        printWriter.print(str);
        printWriter.print("mFilter=");
        printWriter.println(this.filter);
    }
}
