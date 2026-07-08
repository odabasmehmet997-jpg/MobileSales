package io.reactivex.internal.operators.flowable

import androidx.core.location.LocationRequestCompat
import io.reactivex.FlowableSubscriber
import io.reactivex.disposables.Disposable
import io.reactivex.internal.subscriptions.SubscriptionHelper
import io.reactivex.plugins.RxJavaPlugins
import org.reactivestreams.Subscription
import java.util.concurrent.atomic.AtomicReference

internal class FlowableBufferBoundaryBufferCloseSubscriber<T, C : MutableCollection<in T?>?>(
    val parent: FlowableBufferBoundaryBufferBoundarySubscriber<T?, C?, *, *>,
    val index: Long
) : AtomicReference<Subscription>(), FlowableSubscriber<Any?>, Disposable {
    // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    override fun onSubscribe(subscription: Subscription?) {
        SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL)
    } 
    override fun onNext(obj: Any?) {
        val subscription = get()
        val subscriptionHelper = SubscriptionHelper.CANCELLED
        if (subscriptionHelper != subscription) {
            lazySet(subscriptionHelper)
            subscription.cancel()
            this.parent.close(this, this.index)
        }
    }

    // org.reactivestreams.Subscriber
    override fun onError(th: Throwable?) {
        val subscription = get()
        val subscriptionHelper = SubscriptionHelper.CANCELLED
        if (subscriptionHelper != subscription) {
            lazySet(subscriptionHelper)
            this.parent.boundaryError(this, th)
        } else {
            RxJavaPlugins.onError(th)
        }
    }

    // org.reactivestreams.Subscriber
    override fun onComplete() {
        val subscription = get()
        val subscriptionHelper = SubscriptionHelper.CANCELLED
        if (subscriptionHelper != subscription) {
            lazySet(subscriptionHelper)
            this.parent.close(this, this.index)
        }
    }

    // io.reactivex.disposables.Disposable
    override fun dispose() {
        SubscriptionHelper.cancel(this)
    }

    // io.reactivex.disposables.Disposable
    override fun isDisposed(): Boolean {
        return SubscriptionHelper.CANCELLED == this.get()
    }

    companion object {
        private val serialVersionUID = -8498650778633225126L
    }
}
