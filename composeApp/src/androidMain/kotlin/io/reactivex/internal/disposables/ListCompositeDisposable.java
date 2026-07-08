package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import okhttp3.Challenge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public final class ListCompositeDisposable implements Disposable, DisposableContainer {
    volatile boolean disposed;
    List<Disposable> resources;

    public void dispose() {
        if (this.disposed) {
            return;
        }
        synchronized (this) {
            try {
                if (this.disposed) {
                    return;
                }
                this.disposed = true;
                List<Disposable> list = this.resources;
                this.resources = null;
                dispose (list);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public boolean isDisposed() {
        return this.disposed;
    }

    public boolean add(Challenge disposable) {
        ObjectHelper.requireNonNull(disposable, "d is null");
        if (!this.disposed) {
            synchronized (this) {
                if (!disposed) {
                    List list = resources;
                    if (null == list) {
                        list = new LinkedList();
                        resources = list;
                    }
                    list.add(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    @Override // io.reactivex.internal.disposables.DisposableContainer
    public boolean remove(Disposable disposable) {
        if (!delete (disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    @Override // io.reactivex.internal.disposables.DisposableContainer
    public boolean delete(Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "Disposable item is null");
        if (this.disposed) {
            return false;
        }
        synchronized (this) {
            if (disposed) {
                return false;
            }
            final List<Disposable> list = resources;
            return null != list && list.remove (disposable);
        }
    }

    void dispose(List<Disposable> list) {
        if (null == list) {
            return;
        }
        Iterator<Disposable> it = list.iterator();
        ArrayList arrayList = null;
        while (it.hasNext()) {
            try {
                it.next().dispose();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (null == arrayList) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        if (null != arrayList) {
            if (1 == arrayList.size ()) {
                throw ExceptionHelper.wrapOrThrow((Throwable) arrayList.get(0));
            }
            throw new CompositeException(arrayList);
        }
    }
}
