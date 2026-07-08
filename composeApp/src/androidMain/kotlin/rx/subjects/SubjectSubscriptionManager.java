package rx.subjects;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.subscriptions.Subscriptions;

final class SubjectSubscriptionManager<T> extends AtomicReference<SubjectSubscriptionManager.State<T>> implements Observable.OnSubscribe<T> {
    private static final long serialVersionUID = 6035251036011671568L;
    boolean active;
    volatile Object latest;
    Action1<SubjectObserver<T>> onAdded;
    Action1<SubjectObserver<T>> onStart;
    Action1<SubjectObserver<T>> onTerminated;
    public SubjectSubscriptionManager() {
        super();
        this.active = true;
        this.onStart = Actions.empty();
        this.onAdded = Actions.empty();
        this.onTerminated = Actions.empty();
    }
    public void call(Subscriber<? super T> subscriber) {
        SubjectObserver<T> subjectObserver = new SubjectObserver<>(subscriber);
        addUnsubscriber(subscriber, subjectObserver);
        this.onStart.call(subjectObserver);
        if (!subscriber.isUnsubscribed() && add(subjectObserver) && subscriber.isUnsubscribed()) {
            remove(subjectObserver);
        }
    }
    void addUnsubscriber(Subscriber<? super T> subscriber, final SubjectObserver<T> subjectObserver) {
        subscriber.add(Subscriptions.create(new Action0() {
            public void call() {
                SubjectSubscriptionManager.this.remove(subjectObserver);
            }
        }));
    }
    void setLatest(Object obj) {
        this.latest = obj;
    }
    Object getLatest() {
        return this.latest;
    }
    SubjectObserver<T>[] observers() {
        return get().observers;
    }
    boolean add(SubjectObserver<T> subjectObserver) {
        State<T> state;
        do {
            state = get();
            if (state.terminated) {
                this.onTerminated.call(subjectObserver);
                return false;
            }
        } while (!compareAndSet(state, state.add(subjectObserver)));
        this.onAdded.call(subjectObserver);
        return true;
    }
    public boolean compareAndSet(State<T> state, State<T> add) {
        return state.compareAndSet(state, add);
    }
    void remove(SubjectObserver<T> subjectObserver) {
        State<T> state;
        State<T> stateRemove;
        do {
            state = get();
            if (state.terminated || (stateRemove = state.remove(subjectObserver)) == state) {
                return;
            }
        } while (!compareAndSet(state, stateRemove));
    }
    SubjectObserver<T>[] next(Object obj) {
        setLatest(obj);
        return get().observers;
    }
    SubjectObserver<T>[] terminate(Object obj) {
        setLatest(obj);
        this.active = false;
        if (get().terminated) {
            return State.NO_OBSERVERS;
        }
        return getAndSet(State.TERMINATED).observers;
    }
    protected static final class State<T> {
        static final State EMPTY;
        static final SubjectObserver[] NO_OBSERVERS;
        static final State TERMINATED;
        final SubjectObserver[] observers;
        final boolean terminated;
        static {
            SubjectObserver[] subjectObserverArr = new SubjectObserver[0];
            NO_OBSERVERS = subjectObserverArr;
            TERMINATED = new State(true, subjectObserverArr);
            EMPTY = new State(false, subjectObserverArr);
        }
        public State(boolean z, SubjectObserver[] subjectObserverArr) {
            this.terminated = z;
            this.observers = subjectObserverArr;
        }
        public State add(SubjectObserver subjectObserver) {
            SubjectObserver[] subjectObserverArr = this.observers;
            int length = subjectObserverArr.length;
            SubjectObserver[] subjectObserverArr2 = new SubjectObserver[length + 1];
            System.arraycopy(subjectObserverArr, 0, subjectObserverArr2, 0, length);
            subjectObserverArr2[length] = subjectObserver;
            return new State(this.terminated, subjectObserverArr2);
        }
        public State remove(SubjectObserver subjectObserver) {
            SubjectObserver[] subjectObserverArr = this.observers;
            int length = subjectObserverArr.length;
            if (length == 1 && subjectObserverArr[0] == subjectObserver) {
                return EMPTY;
            }
            if (length == 0) {
                return this;
            }
            int i2 = length - 1;
            SubjectObserver[] subjectObserverArr2 = new SubjectObserver[i2];
            int i3 = 0;
            for (SubjectObserver subjectObserver2 : subjectObserverArr) {
                if (subjectObserver2 != subjectObserver) {
                    if (i3 == i2) {
                        return this;
                    }
                    subjectObserverArr2[i3] = subjectObserver2;
                    i3++;
                }
            }
            if (i3 == 0) {
                return EMPTY;
            }
            if (i3 < i2) {
                SubjectObserver[] subjectObserverArr3 = new SubjectObserver[i3];
                System.arraycopy(subjectObserverArr2, 0, subjectObserverArr3, 0, i3);
                subjectObserverArr2 = subjectObserverArr3;
            }
            return new State(this.terminated, subjectObserverArr2);
        }
        public boolean compareAndSet(State<T> state, State<T> add) {
            return false;
        }
    }
    protected static final class SubjectObserver<T> implements Observer<T> {
        final Subscriber<? super T> actual;
        boolean first = true;

        public SubjectObserver(Subscriber<? super T> subscriber) {
            this.actual = subscriber;
        }

        @Override // rx.Observer
        public void onNext(Object t) {
            this.actual.onNext(t);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.actual.onCompleted();
        }
    }
}
