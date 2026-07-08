package rx.internal.operators;

import rx.Subscriber;


final class OnSubscribeCreate$DropEmitter<T> extends OnSubscribeCreate$NoOverflowBaseEmitter<T> {
    private static final long serialVersionUID = 8360058422307496563L;

    @Override // rx.internal.operators.OnSubscribeCreate$NoOverflowBaseEmitter
    void onOverflow() {
    }

    public OnSubscribeCreate$DropEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    }
}
