package io.reactivex.internal.operators.flowable

import com.proje.mobilesales.features.model.Resource
import io.reactivex.internal.queue.SpscLinkedArrayQueue
import io.reactivex.internal.util.BackpressureHelper
import org.reactivestreams.Subscriber
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.Volatile

internal class FlowableCreateBufferAsyncEmitter<T>(subscriber: Subscriber<in T?>?, i2: Int) :
    FlowableCreateBaseEmitter<T?>(subscriber) {
    var done: Boolean = false
    var error: Throwable? = null
    val queue: SpscLinkedArrayQueue<T?>
    val wip: AtomicInteger
    init {
        this.queue = SpscLinkedArrayQueue<T?>(i2)
        this.wip = AtomicInteger()
    }
    override fun onNext(t: Resource.Success<*>?) {
        if (this.done || isCancelled) {
            return
        }
        if (null == t) {
            onError(NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."))
        } else {
            this.queue.offer(t as T)
            drain()
        }
    }
    override fun tryOnError(th: Throwable?): Boolean {
        var th = th
        if (this.done || isCancelled) {
            return false
        }
        if (null == th) {
            th =
                NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.")
        }
        this.error = th
        this.done = true
        drain()
        return true
    }
    override fun onComplete() {
        this.done = true
        drain()
    }
    override fun onRequested() {
        drain()
    }
    override fun onUnsubscribed() {
        if (0 == wip.getAndIncrement()) {
            this.queue.clear()
        }
    }
    fun drain() {
        if (0 != wip.getAndIncrement()) {
            return
        }
        val subscriber = this.downstream
        val spscLinkedArrayQueue = this.queue
        var i2 = 1
        do {
            val j2 = get()
            var j3: Long = 0
            while (j3 != j2) {
                if (isCancelled) {
                    spscLinkedArrayQueue.clear()
                    return
                }
                val z = this.done
                val poll = spscLinkedArrayQueue.poll()
                val z2 = null == poll
                if (z && z2) {
                    val th = this.error
                    if (null != th) {
                        error(th)
                        return
                    } else {
                        complete()
                        return
                    }
                }
                if (z2) {
                    break
                }
                subscriber.onNext(poll)
                j3++
            }
            if (j3 == j2) {
                if (isCancelled) {
                    spscLinkedArrayQueue.clear()
                    return
                }
                val z3 = this.done
                val isEmpty = spscLinkedArrayQueue.isEmpty
                if (z3 && isEmpty) {
                    val th2 = this.error
                    if (null != th2) {
                        error(th2)
                        return
                    } else {
                        complete()
                        return
                    }
                }
            }
            if (0L != j3) {
                BackpressureHelper.produced(this, j3)
            }
            i2 = this.wip.addAndGet(-i2)
        } while (0 != i2)
    }

    override fun toShort(): Short {
        TODO("Not yet implemented")
    }

    override fun toByte(): Byte {
        TODO("Not yet implemented")
    }

    companion object {
        private const val serialVersionUID = 2427151001689639875L
    }
}
