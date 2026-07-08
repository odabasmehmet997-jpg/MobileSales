package io.reactivex.internal.operators.flowable;



public class  FlowableTimeoutTimedTimeoutTask(long idx, FlowableTimeoutTimedTimeoutSupport parent) implements Runnable {

    public void run() {
        this.parent.onTimeout(this.idx);
    }
}
