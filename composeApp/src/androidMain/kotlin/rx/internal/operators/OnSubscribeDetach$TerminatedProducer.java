package rx.internal.operators;

import rx.Producer;


enum OnSubscribeDetach$TerminatedProducer implements Producer {
    INSTANCE;

    @Override // rx.Producer
    public void request(long j2) {
    }
}
