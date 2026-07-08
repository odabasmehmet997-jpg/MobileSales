package io.reactivex.internal.operators.flowable

import androidx.core.location.LocationRequestCompat
import io.reactivex.FlowableSubscriber
import io.reactivex.exceptions.Exceptions
import io.reactivex.exceptions.MissingBackpressureException
import io.reactivex.functions.Function
import io.reactivex.internal.functions.ObjectHelper
import io.reactivex.internal.fuseable.SimplePlainQueue
import io.reactivex.internal.fuseable.SimpleQueue
import io.reactivex.internal.queue.SpscArrayQueue
import io.reactivex.internal.queue.SpscLinkedArrayQueue
import io.reactivex.internal.subscriptions.SubscriptionHelper
import io.reactivex.internal.util.AtomicThrowable
import io.reactivex.internal.util.BackpressureHelper
import io.reactivex.internal.util.ExceptionHelper
import io.reactivex.plugins.RxJavaPlugins
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.Volatile
import kotlin.math.max

internal class FlowableFlatMapMergeSubscriber<T, U>(
    subscriber: Subscriber<in U?>,
    function: Function<in T?, out Publisher<out U?>>,
    z: Boolean,
    i2: Int,
    i3: Int
) : AtomicInteger(), FlowableSubscriber<T?>, Subscription {
    val bufferSize: Int

    @Volatile
    var cancelled: Boolean = false
    val delayErrors: Boolean

    @Volatile
    var done: Boolean = false
    val downstream: Subscriber<in U?>
    val errs: AtomicThrowable = AtomicThrowable()
    var lastId: Long = 0
    var lastIndex: Int = 0
    val mapper: Function<in T?, out Publisher<out U?>>
    val maxConcurrency: Int

    @Volatile
    var queue: SimplePlainQueue<U?>? = null
    val requested: AtomicLong
    var scalarEmitted: Int = 0
    val scalarLimit: Int
    val subscribers: AtomicReference<Array<FlowableFlatMapInnerSubscriber<*, *>>?>
    var uniqueId: Long = 0
    var upstream: Subscription? = null

    init {
        val atomicReference = AtomicReference<Array<FlowableFlatMapInnerSubscriber<*, *>>?>()
        this.subscribers = atomicReference
        this.requested = AtomicLong()
        this.downstream = subscriber
        this.mapper = function
        this.delayErrors = z
        this.maxConcurrency = i2
        this.bufferSize = i3
        this.scalarLimit = max(1, i2 shr 1)
        atomicReference.lazySet(EMPTY as Array<FlowableFlatMapInnerSubscriber<*, *>>?)
    }

    override fun onSubscribe(subscription: Subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription
            this.downstream.onSubscribe(this)
            if (this.cancelled) {
                return
            }
            val i2 = this.maxConcurrency
            if (Int.MAX_VALUE == i2) {
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL)
            } else {
                subscription.request(i2.toLong())
            }
        }
    }

    override fun onNext(t: Any?) {
        if (this.done) {
            return
        }
        try {
            val publisher: Publisher<*> =
                ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher")
            if (publisher is Callable<*>) {
                try {
                    val call: Any? = (publisher as Callable<*>).call()
                    if (null != call) {
                        tryEmitScalar(call as U?)
                        return
                    }
                    if (Int.MAX_VALUE == maxConcurrency || this.cancelled) {
                        return
                    }
                    val i2 = this.scalarEmitted + 1
                    this.scalarEmitted = i2
                    val i3 = this.scalarLimit
                    if (i2 == i3) {
                        this.scalarEmitted = 0
                        this.upstream!!.request(i3.toLong())
                        return
                    }
                    return
                } catch (th: Throwable) {
                    Exceptions.throwIfFatal(th)
                    this.errs.addThrowable(th)
                    drain()
                    return
                }
            }
            val j2 = this.uniqueId
            this.uniqueId = 1 + j2
            val flowableFlatMapInnerSubscriber: Subscriber<in Nothing> =
                FlowableFlatMapInnerSubscriber<Any?, Any?>(this as FlowableFlatMapMergeSubscriber<in Any, in Any>?, j2)
            if (addInner(flowableFlatMapInnerSubscriber)) {
                publisher.subscribe(flowableFlatMapInnerSubscriber)
            }
        } catch (th2: Throwable) {
            Exceptions.throwIfFatal(th2)
            this.upstream!!.cancel()
            onError(th2)
        }
    }

    fun addInner(flowableFlatMapInnerSubscriber: Subscriber<out Any?>?): Boolean {
        var flowableFlatMapInnerSubscriberArr: Array<FlowableFlatMapInnerSubscriber<*, *>?>
        var flowableFlatMapInnerSubscriberArr2: Array<FlowableFlatMapInnerSubscriber<*, *>?>?
        do {
            flowableFlatMapInnerSubscriberArr = this.subscribers.get()
            if (flowableFlatMapInnerSubscriberArr == CANCELLED) {
                flowableFlatMapInnerSubscriber.dispose()
                return false
            }
            val length = flowableFlatMapInnerSubscriberArr.size
            flowableFlatMapInnerSubscriberArr2 = arrayOfNulls<FlowableFlatMapInnerSubscriber<*, *>>(length + 1)
            System.arraycopy(flowableFlatMapInnerSubscriberArr, 0, flowableFlatMapInnerSubscriberArr2, 0, length)
            flowableFlatMapInnerSubscriberArr2[length] = flowableFlatMapInnerSubscriber
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(
                this.subscribers,
                flowableFlatMapInnerSubscriberArr,
                flowableFlatMapInnerSubscriberArr2
            )
        )
        return true
    }

    fun removeInner(flowableFlatMapInnerSubscriber: FlowableFlatMapInnerSubscriber<T?, U?>?) {
        var flowableFlatMapInnerSubscriberArr: Array<FlowableFlatMapInnerSubscriber<*, *>?>
        var flowableFlatMapInnerSubscriberArr2: Array<FlowableFlatMapInnerSubscriber<*, *>?>?
        do {
            flowableFlatMapInnerSubscriberArr = this.subscribers.get()
            val length = flowableFlatMapInnerSubscriberArr.size
            if (0 == length) {
                return
            }
            var i2 = 0
            while (true) {
                if (i2 >= length) {
                    i2 = -1
                    break
                } else if (flowableFlatMapInnerSubscriberArr[i2] == flowableFlatMapInnerSubscriber) {
                    break
                } else {
                    i2++
                }
            }
            if (0 > i2) {
                return
            }
            if (1 == length) {
                flowableFlatMapInnerSubscriberArr2 = EMPTY
            } else {
                val flowableFlatMapInnerSubscriberArr3 = arrayOfNulls<FlowableFlatMapInnerSubscriber<*, *>>(length - 1)
                System.arraycopy(flowableFlatMapInnerSubscriberArr, 0, flowableFlatMapInnerSubscriberArr3, 0, i2)
                System.arraycopy(
                    flowableFlatMapInnerSubscriberArr,
                    i2 + 1,
                    flowableFlatMapInnerSubscriberArr3,
                    i2,
                    (length - i2) - 1
                )
                flowableFlatMapInnerSubscriberArr2 = flowableFlatMapInnerSubscriberArr3
            }
        } while (!LifecycleKtExternalSyntheticBackportWithForwarding0.m(
                this.subscribers,
                flowableFlatMapInnerSubscriberArr,
                flowableFlatMapInnerSubscriberArr2
            )
        )
    }

    val mainQueue: SimpleQueue<U?>
        get() {
            var simplePlainQueue = this.queue
            if (null == simplePlainQueue) {
                if (Int.MAX_VALUE == maxConcurrency) {
                    simplePlainQueue = SpscLinkedArrayQueue<U?>(this.bufferSize)
                } else {
                    simplePlainQueue = SpscArrayQueue<U?>(this.maxConcurrency)
                }
                this.queue = simplePlainQueue
            }
            return simplePlainQueue
        }

    fun tryEmitScalar(u: U?) {
        if (0 == this.get() && compareAndSet(0, 1)) {
            val j2 = this.requested.get()
            var simpleQueue: SimpleQueue<U?>? = this.queue
            if (0L != j2 && (null == simpleQueue || simpleQueue.isEmpty())) {
                this.downstream.onNext(u)
                if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                    this.requested.decrementAndGet()
                }
                if (Int.MAX_VALUE != maxConcurrency && !this.cancelled) {
                    val i2 = this.scalarEmitted + 1
                    this.scalarEmitted = i2
                    val i3 = this.scalarLimit
                    if (i2 == i3) {
                        this.scalarEmitted = 0
                        this.upstream!!.request(i3.toLong())
                    }
                }
            } else {
                if (null == simpleQueue) {
                    simpleQueue = this.mainQueue
                }
                if (!simpleQueue.offer(u)) {
                    onError(IllegalStateException("Scalar queue full?!"))
                    return
                }
            }
            if (0 == this.decrementAndGet()) {
                return
            }
        } else if (!this.mainQueue.offer(u)) {
            onError(IllegalStateException("Scalar queue full?!"))
            return
        } else if (0 != this.getAndIncrement()) {
            return
        }
        drainLoop()
    }

    fun getInnerQueue(flowableFlatMapInnerSubscriber: FlowableFlatMapInnerSubscriber<T?, U?>): SimpleQueue<U?> {
        val simpleQueue = flowableFlatMapInnerSubscriber.queue
        if (null != simpleQueue) {
            return simpleQueue
        }
        val spscArrayQueue: SpscArrayQueue<*> = SpscArrayQueue<Any?>(this.bufferSize)
        flowableFlatMapInnerSubscriber.queue = spscArrayQueue
        return spscArrayQueue
    }

    fun tryEmit(u: U?, flowableFlatMapInnerSubscriber: FlowableFlatMapInnerSubscriber<T?, U?>) {
        if (0 == this.get() && compareAndSet(0, 1)) {
            val j2 = this.requested.get()
            var simpleQueue = flowableFlatMapInnerSubscriber.queue
            if (0L != j2 && (null == simpleQueue || simpleQueue.isEmpty())) {
                this.downstream.onNext(u)
                if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
                    this.requested.decrementAndGet()
                }
                flowableFlatMapInnerSubscriber.requestMore(1L)
            } else {
                if (null == simpleQueue) {
                    simpleQueue = getInnerQueue(flowableFlatMapInnerSubscriber)
                }
                if (!simpleQueue.offer(u)) {
                    onError(MissingBackpressureException("Inner queue full?!"))
                    return
                }
            }
            if (0 == this.decrementAndGet()) {
                return
            }
        } else {
            var simpleQueue2: SimpleQueue<*>? = flowableFlatMapInnerSubscriber.queue
            if (null == simpleQueue2) {
                simpleQueue2 = SpscArrayQueue<Any?>(this.bufferSize)
                flowableFlatMapInnerSubscriber.queue = simpleQueue2
            }
            if (!simpleQueue2.offer(u)) {
                onError(MissingBackpressureException("Inner queue full?!"))
                return
            } else if (0 != this.getAndIncrement()) {
                return
            }
        }
        drainLoop()
    }

    // org.reactivestreams.Subscriber
    override fun onError(th: Throwable?) {
        if (this.done) {
            RxJavaPlugins.onError(th)
            return
        }
        if (this.errs.addThrowable(th)) {
            this.done = true
            if (!this.delayErrors) {
                for (flowableFlatMapInnerSubscriber in this.subscribers.getAndSet(CANCELLED)!!) {
                    flowableFlatMapInnerSubscriber.dispose()
                }
            }
            drain()
            return
        }
        RxJavaPlugins.onError(th)
    }

    // org.reactivestreams.Subscriber
    override fun onComplete() {
        if (this.done) {
            return
        }
        this.done = true
        drain()
    }
    override fun request(j2: Long) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2)
            drain()
        }
    }
    override fun cancel() {
        val simplePlainQueue: SimplePlainQueue<U?>?
        if (this.cancelled) {
            return
        }
        this.cancelled = true
        this.upstream!!.cancel()
        disposeAll()
        if (0 != this.getAndIncrement() || null == (queue.also { simplePlainQueue = it })) {
            return
        }
        simplePlainQueue!!.clear()
    }
    fun drain() {
        if (0 == this.getAndIncrement()) {
            drainLoop()
        }
    }
    fun drainLoop() {
        var j2: Long
        var j3: Long
        var z: Boolean
        var i2: Int
        var i3: Int
        var obj: Any?
        val subscriber = this.downstream
        var i4 = 1
        while (!checkTerminate()) {
            val simplePlainQueue = this.queue
            var j4 = this.requested.get()
            val z2 = LocationRequestCompat.PASSIVE_INTERVAL == j4
            var j5: Long = 0
            var j6: Long = 0
            if (null != simplePlainQueue) {
                do {
                    var j7: Long = 0
                    obj = null
                    while (true) {
                        if (0L == j4) {
                            break
                        }
                        val poll = simplePlainQueue.poll()
                        if (checkTerminate()) {
                            return
                        }
                        if (null == poll) {
                            obj = poll
                            break
                        }
                        subscriber.onNext(poll)
                        j6++
                        j7++
                        j4--
                        obj = poll
                    }
                    if (0L != j7) {
                        j4 = if (z2) LocationRequestCompat.PASSIVE_INTERVAL else this.requested.addAndGet(-j7)
                    }
                    if (0L == j4) {
                        break
                    }
                } while (null != obj)
            }
            val z3 = this.done
            val simplePlainQueue2 = this.queue
            val flowableFlatMapInnerSubscriberArr = this.subscribers.get()
            var length = flowableFlatMapInnerSubscriberArr.size
            if (z3 && ((null == simplePlainQueue2 || simplePlainQueue2.isEmpty()) && 0 == length)) {
                val terminate = this.errs.terminate()
                if (terminate !== ExceptionHelper.TERMINATED) {
                    if (null == terminate) {
                        subscriber.onComplete()
                        return
                    } else {
                        subscriber.onError(terminate)
                        return
                    }
                }
                return
            }
            val i5 = i4
            if (0 != length) {
                val j8 = this.lastId
                var i6 = this.lastIndex
                if (length <= i6 || flowableFlatMapInnerSubscriberArr[i6].id != j8) {
                    if (length <= i6) {
                        i6 = 0
                    }
                    var i7 = 0
                    while (i7 < length && flowableFlatMapInnerSubscriberArr[i6].id != j8) {
                        i6++
                        if (i6 == length) {
                            i6 = 0
                        }
                        i7++
                    }
                    this.lastIndex = i6
                    this.lastId = flowableFlatMapInnerSubscriberArr[i6].id
                }
                var i8 = i6
                var z4 = false
                var i9 = 0
                while (true) {
                    if (i9 >= length) {
                        z = z4
                        break
                    }
                    if (checkTerminate()) {
                        return
                    }
                    val flowableFlatMapInnerSubscriber: FlowableFlatMapInnerSubscriber<T?, U?> =
                        flowableFlatMapInnerSubscriberArr[i8]
                    val obj2: Any? = null
                    while (!checkTerminate()) {
                        val simpleQueue = flowableFlatMapInnerSubscriber.queue
                        if (null != simpleQueue) {
                            i2 = length
                            var obj3 = obj2
                            var j9 = j5
                            while (true) {
                                if (j4 == j5) {
                                    break
                                }
                                try {
                                    val poll2 = simpleQueue.poll()
                                    if (null == poll2) {
                                        obj3 = poll2
                                        j5 = 0
                                        break
                                    }
                                    subscriber.onNext(poll2)
                                    if (checkTerminate()) {
                                        return
                                    }
                                    j4--
                                    j9++
                                    obj3 = poll2
                                    j5 = 0
                                } catch (th: Throwable) {
                                    Exceptions.throwIfFatal(th)
                                    flowableFlatMapInnerSubscriber.dispose()
                                    this.errs.addThrowable(th)
                                    if (!this.delayErrors) {
                                        this.upstream!!.cancel()
                                    }
                                    if (checkTerminate()) {
                                        return
                                    }
                                    removeInner(flowableFlatMapInnerSubscriber)
                                    i9++
                                    z4 = true
                                    i3 = 1
                                }
                            }
                        } else {
                            i2 = length
                        }
                        val z5 = flowableFlatMapInnerSubscriber.done
                        val simpleQueue2 = flowableFlatMapInnerSubscriber.queue
                        if (z5 && (null == simpleQueue2 || simpleQueue2.isEmpty())) {
                            removeInner(flowableFlatMapInnerSubscriber)
                            if (checkTerminate()) {
                                return
                            }
                            j6++
                            z4 = true
                        }
                        if (0L == j4) {
                            z = z4
                            break
                        }
                        i8++
                        if (i8 == i2) {
                            i8 = 0
                        }
                        i3 = 1
                        i9 += i3
                        length = i2
                        j5 = 0
                    }
                    return
                }
            }
            j2 = 0
            j3 = j6
            z = false
            if (j3 != j2 && !this.cancelled) {
                this.upstream!!.request(j3)
            }
            if (z) {
                i4 = i5
            } else {
                i4 = addAndGet(-i5)
                if (0 == i4) {
                    return
                }
            }
        }
    }
    fun checkTerminate(): Boolean {
        if (this.cancelled) {
            clearScalarQueue()
            return true
        }
        if (this.delayErrors || null == errs.get()) {
            return false
        }
        clearScalarQueue()
        val terminate = this.errs.terminate()
        if (terminate !== ExceptionHelper.TERMINATED) {
            this.downstream.onError(terminate)
        }
        return true
    }

    fun clearScalarQueue() {
        val simplePlainQueue = this.queue
        if (null != simplePlainQueue) {
            simplePlainQueue.clear()
        }
    }

    fun disposeAll() {
        val andSet: Array<FlowableFlatMapInnerSubscriber<*, *>>
        val flowableFlatMapInnerSubscriberArr = this.subscribers.get()
        val flowableFlatMapInnerSubscriberArr2: Array<FlowableFlatMapInnerSubscriber<*, *>?> = CANCELLED
        if (flowableFlatMapInnerSubscriberArr == flowableFlatMapInnerSubscriberArr2 || (this.subscribers.getAndSet(
                flowableFlatMapInnerSubscriberArr2
            ).also { andSet = it!! }) == flowableFlatMapInnerSubscriberArr2
        ) {
            return
        }
        for (flowableFlatMapInnerSubscriber in andSet) {
            flowableFlatMapInnerSubscriber.dispose()
        }
        val terminate = this.errs.terminate()
        if (null == terminate || terminate === ExceptionHelper.TERMINATED) {
            return
        }
        RxJavaPlugins.onError(terminate)
    }

    fun innerError(flowableFlatMapInnerSubscriber: FlowableFlatMapInnerSubscriber<T?, U?>, th: Throwable?) {
        if (this.errs.addThrowable(th)) {
            flowableFlatMapInnerSubscriber.done = true
            if (!this.delayErrors) {
                this.upstream!!.cancel()
                for (flowableFlatMapInnerSubscriber2 in this.subscribers.getAndSet(CANCELLED)!!) {
                    flowableFlatMapInnerSubscriber2.dispose()
                }
            }
            drain()
            return
        }
        RxJavaPlugins.onError(th)
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }

    override fun toByte(): Byte {
        TODO("Not yet implemented")
    }

    companion object {
        private val serialVersionUID = -2117620485640801370L
        val EMPTY: Array<FlowableFlatMapInnerSubscriber<*, *>?> = arrayOfNulls<FlowableFlatMapInnerSubscriber<*, *>>(0)
        val CANCELLED: Array<FlowableFlatMapInnerSubscriber<*, *>?> =
            arrayOfNulls<FlowableFlatMapInnerSubscriber<*, *>>(0)
    }
}
