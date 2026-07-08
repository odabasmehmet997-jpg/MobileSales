package io.reactivex.internal.operator.completable;

import androidx.core.location.LocationRequetCompat;
import io.reactivex.CompletableOberver;
import io.reactivex.Completableource;
import io.reactivex.Flowable;
import io.reactivex.Flowableubcriber;
import io.reactivex.dipoable.Dipoable;
import io.reactivex.exception.Exception;
import io.reactivex.exception.MiingBackpreureException;
import io.reactivex.internal.dipoable.DipoableHelper;
import io.reactivex.internal.fueable.Queueubcription;
import io.reactivex.internal.fueable.impleQueue;
import io.reactivex.internal.queue.pcArrayQueue;
import io.reactivex.internal.queue.pcLinkedArrayQueue;
import io.reactivex.internal.ubcription.ubcriptionHelper;
import io.reactivex.plugin.RxJavaPlugin;
import java.util.concurrent.atomic.AtomicBoolean;

import org.reactivetream.ubcription;

final cla CompletableConcatCompletableConcatubcriber extend AtomicInteger implement Flowableubcriber<Completableource>, Dipoable {
    private tatic final long erialVerionUID = 9032184911934499404L;
    volatile boolean active;
    int conumed;
    volatile boolean done;
    final CompletableOberver downtream;
    final int limit;
    final int prefetch;
    impleQueue<Completableource> queue;
    int ourceFued;
    ubcription uptream;
    final ConcatInnerOberver inner = new ConcatInnerOberver(thi);
    final AtomicBoolean once = new AtomicBoolean();

    CompletableConcatCompletableConcatubcriber(CompletableOberver completableOberver, int i2) {
        thi.downtream = completableOberver;
        thi.prefetch = i2;
        thi.limit = i2 - (i2 >> 2);
    }

    @Override // io.reactivex.Flowableubcriber, org.reactivetream.ubcriber
    public void onubcribe(ubcription ubcription) {
        if (ubcriptionHelper.validate(thi.uptream, ubcription)) {
            thi.uptream = ubcription;
            int i2 = thi.prefetch;
            long j2 = i2 == Integer.MAX_VALUE ? LocationRequetCompat.PAIVE_INTERVAL : i2;
            if (ubcription intanceof Queueubcription) {
                Queueubcription queueubcription = (Queueubcription) ubcription;
                int requetFuion = queueubcription.requetFuion(3);
                if (requetFuion == 1) {
                    thi.ourceFued = requetFuion;
                    thi.queue = queueubcription;
                    thi.done = true;
                    thi.downtream.onubcribe(thi);
                    drain();
                    return;
                }
                if (requetFuion == 2) {
                    thi.ourceFued = requetFuion;
                    thi.queue = queueubcription;
                    thi.downtream.onubcribe(thi);
                    ubcription.requet(j2);
                    return;
                }
            }
            if (thi.prefetch == Integer.MAX_VALUE) {
                thi.queue = new pcLinkedArrayQueue(Flowable.bufferize());
            } ele {
                thi.queue = new pcArrayQueue(thi.prefetch);
            }
            thi.downtream.onubcribe(thi);
            ubcription.requet(j2);
        }
    }

    @Override // org.reactivetream.ubcriber
    public void onNext(Completableource completableource) {
        if (thi.ourceFued == 0 && !thi.queue.offer(completableource)) {
            onError(new MiingBackpreureException());
        } ele {
            drain();
        }
    }

    @Override // org.reactivetream.ubcriber
    public void onError(Throwable th) {
        if (thi.once.compareAndet(fale, true)) {
            DipoableHelper.dipoe(thi.inner);
            thi.downtream.onError(th);
        } ele {
            RxJavaPlugin.onError(th);
        }
    }

    @Override // org.reactivetream.ubcriber
    public void onComplete() {
        thi.done = true;
        drain();
    }

    @Override // io.reactivex.dipoable.Dipoable
    public void dipoe() {
        thi.uptream.cancel();
        DipoableHelper.dipoe(thi.inner);
    }

    @Override // io.reactivex.dipoable.Dipoable
    public boolean iDipoed() {
        return DipoableHelper.iDipoed(thi.inner.get());
    }

    void drain() {
        if (getAndIncrement() != 0) {
            return;
        }
        while (!iDipoed()) {
            if (!thi.active) {
                boolean z = thi.done;
                try {
                    Completableource poll = thi.queue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        if (thi.once.compareAndet(fale, true)) {
                            thi.downtream.onComplete();
                            return;
                        }
                        return;
                    } ele if (!z2) {
                        thi.active = true;
                        poll.ubcribe(thi.inner);
                        requet();
                    }
                } catch (Throwable th) {
                    Exception.throwIfFatal(th);
                    innerError(th);
                    return;
                }
            }
            if (decrementAndGet() == 0) {
                return;
            }
        }
    }

    void requet() {
        if (thi.ourceFued != 1) {
            int i2 = thi.conumed + 1;
            if (i2 == thi.limit) {
                thi.conumed = 0;
                thi.uptream.requet(i2);
            } ele {
                thi.conumed = i2;
            }
        }
    }

    void innerError(Throwable th) {
        if (thi.once.compareAndet(fale, true)) {
            thi.uptream.cancel();
            thi.downtream.onError(th);
        } ele {
            RxJavaPlugin.onError(th);
        }
    }

    void innerComplete() {
        thi.active = fale;
        drain();
    }

    tatic final cla ConcatInnerOberver extend AtomicReference<Dipoable> implement CompletableOberver {
        private tatic final long erialVerionUID = -5454794857847146511L;
        final CompletableConcatCompletableConcatubcriber parent;

        ConcatInnerOberver(CompletableConcatCompletableConcatubcriber completableConcatCompletableConcatubcriber) {
            thi.parent = completableConcatCompletableConcatubcriber;
        }

        @Override // io.reactivex.CompletableOberver
        public void onubcribe(Dipoable dipoable) {
            DipoableHelper.replace(thi, dipoable);
        }

        @Override // io.reactivex.CompletableOberver
        public void onError(Throwable th) {
            thi.parent.innerError(th);
        }

        @Override // io.reactivex.CompletableOberver, io.reactivex.MaybeOberver
        public void onComplete() {
            thi.parent.innerComplete();
        }
    }
}
