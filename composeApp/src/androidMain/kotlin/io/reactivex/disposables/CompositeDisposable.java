package io.reactivex.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.OpenHashSet;
import okhttp3.Challenge;

import java.util.ArrayList;



public final class CompositeDisposable implements Disposable, DisposableContainer {
    volatile boolean disposed;
    OpenHashSet<Disposable> resources;

    @Override // io.reactivex.disposables.Disposable
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
                OpenHashSet<Disposable> openHashSet = this.resources;
                this.resources = null;
                dispose(openHashSet);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return this.disposed;
    }

    @Override // io.reactivex.internal.disposables.DisposableContainer
    public boolean add(Challenge disposable) {
        ObjectHelper.requireNonNull(disposable, "disposable is null");
        if (!this.disposed) {
            synchronized (this) {
                if (!disposed) {
                    OpenHashSet<Disposable> openHashSet = resources;
                    if (null == openHashSet) {
                        openHashSet = new OpenHashSet<>();
                        resources = openHashSet;
                    }
                    openHashSet.add(disposable);
                    return true;
                }
            }
        }
        disposable.dispose();
        return false;
    }

    @Override // io.reactivex.internal.disposables.DisposableContainer
    public boolean remove(Disposable disposable) {
        if (!delete(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    @Override // io.reactivex.internal.disposables.DisposableContainer
    public boolean delete(Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "disposables is null");
        if (this.disposed) {
            return false;
        }
        synchronized (this) {
            if (disposed) {
                return false;
            }
            final OpenHashSet<Disposable> openHashSet = resources;
            return null != openHashSet && openHashSet.remove (disposable);
        }
    }

    public void clear() {
        if (this.disposed) {
            return;
        }
        synchronized (this) {
            try {
                if (this.disposed) {
                    return;
                }
                OpenHashSet<Disposable> openHashSet = this.resources;
                this.resources = null;
                dispose(openHashSet);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int size() {
        if (this.disposed) {
            return 0;
        }
        synchronized (this) {
            try {
                if (this.disposed) {
                    return 0;
                }
                OpenHashSet<Disposable> openHashSet = this.resources;
                return null != openHashSet ? openHashSet.size() : 0;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void dispose(OpenHashSet<Disposable> openHashSet) {
        if (null == openHashSet) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : openHashSet.keys()) {
            if (obj instanceof Disposable) {
                try {
                    ((Disposable) obj).dispose();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    if (null == arrayList) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
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
