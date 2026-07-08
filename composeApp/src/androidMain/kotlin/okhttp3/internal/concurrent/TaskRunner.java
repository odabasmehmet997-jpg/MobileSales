package okhttp3.internal.concurrent;

import androidx.core.location.LocationRequestCompat;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static kotlin.jvm.internal.Intrinsics.*;

public final class TaskRunner {
    public static final Companion Companion = new Companion(null);
    public static final TaskRunner INSTANCE = new TaskRunner(new RealBackend(Util.threadFactory(stringPlus(Util.okHttpName, " TaskRunner"), true)));
    private static final Logger logger;
    static {
        final Logger logger2 = Logger.getLogger(TaskRunner.class.getName());
        checkNotNullExpressionValue(logger2, "getLogger(TaskRunner.class.getName())");
        logger = logger2;
    }
    private final Backend backend;
    private final List<TaskQueue> busyQueues;
    private final List<TaskQueue> readyQueues;
    private final Runnable runnable;
    private boolean coordinatorWaiting;
    private long coordinatorWakeUpAt;
    private int nextQueueName;
    public TaskRunner(final Backend backend) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        this.backend = backend;
        nextQueueName = 10000;
        busyQueues = new ArrayList();
        readyQueues = new ArrayList();
        runnable = new Runnable() {
            private TaskRunner this0;

            public void run() {
                Task taskAwaitTaskToRun;
                long jNanoTime;
                while (true) {
                    final TaskRunner taskRunner = this.this0;
                    synchronized (taskRunner) {
                        taskAwaitTaskToRun = taskRunner.awaitTaskToRun();
                    }
                    if (null == taskAwaitTaskToRun) {
                        return;
                    }
                    final TaskQueue queueokhttp = taskAwaitTaskToRun.getQueueokhttp();
                    checkNotNull(queueokhttp);
                    final TaskRunner taskRunner2 = this.this0;
                    final boolean zIsLoggable = Companion.getLogger().isLoggable(Level.FINE);
                    if (zIsLoggable) {
                        jNanoTime = queueokhttp.getTaskRunnerokhttp().getBackend().nanoTime();
                        TaskLogger.log(taskAwaitTaskToRun, queueokhttp, "starting");
                    } else {
                        jNanoTime = -1;
                    }
                    try {
                        taskRunner2.runTask(taskAwaitTaskToRun);
                        Unit unit = Unit.INSTANCE;
                        if (zIsLoggable) {
                            TaskLogger.log(taskAwaitTaskToRun, queueokhttp, stringPlus("finished run in ", TaskLogger.formatDuration(queueokhttp.getTaskRunnerokhttp().getBackend().nanoTime() - jNanoTime)));
                        }
                    } catch (final Throwable th) {
                        if (zIsLoggable) {
                            TaskLogger.log(taskAwaitTaskToRun, queueokhttp, stringPlus("failed a run in ", TaskLogger.formatDuration(queueokhttp.getTaskRunnerokhttp().getBackend().nanoTime() - jNanoTime)));
                        }
                        throw th;
                    }
                }
            }
        };
    }
    public Backend getBackend() {
        return backend;
    }
    public TaskQueue newQueue() {
        final int i2;
        synchronized (this) {
            i2 = nextQueueName;
            nextQueueName = i2 + 1;
        }
        return new TaskQueue(this, stringPlus("Q", Integer.valueOf(i2)));
    }
    public List<TaskQueue> activeQueues() {
        final List<TaskQueue> listPlus;
        synchronized (this) {
            listPlus = CollectionsKt.plus((Collection) busyQueues, (Iterable) readyQueues);
        }
        return listPlus;
    }
    public void cancelAll() {
        int size = busyQueues.size() - 1;
        if (0 <= size) {
            while (true) {
                final int i2 = size - 1;
                busyQueues.get(size).cancelAllAndDecideokhttp();
                if (0 > i2) {
                    break;
                } else {
                    size = i2;
                }
            }
        }
        int size2 = readyQueues.size() - 1;
        if (0 > size2) {
            return;
        }
        while (true) {
            final int i3 = size2 - 1;
            final TaskQueue taskQueue = readyQueues.get(size2);
            taskQueue.cancelAllAndDecideokhttp();
            if (taskQueue.getFutureTasksokhttp().isEmpty()) {
                readyQueues.remove(size2);
            }
            if (0 > i3) {
                return;
            } else {
                size2 = i3;
            }
        }
    }
    private void afterRun(final Task task, final long j2) {
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            final TaskQueue queueokhttp = task.getQueueokhttp();
            checkNotNull(queueokhttp);
            if (queueokhttp.getActiveTaskokhttp() != task) {
                throw new IllegalStateException("Check failed.");
            }
            final boolean cancelActiveTaskokhttp = queueokhttp.getCancelActiveTaskokhttp();
            queueokhttp.setCancelActiveTaskokhttp(false);
            queueokhttp.setActiveTaskokhttp(null);
            busyQueues.remove(queueokhttp);
            if (-1 != j2 && !cancelActiveTaskokhttp && !queueokhttp.getShutdownokhttp()) {
                queueokhttp.scheduleAndDecideokhttp(task, j2, true);
            }
            if (queueokhttp.getFutureTasksokhttp().isEmpty()) {
                return;
            }
            readyQueues.add(queueokhttp);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }
    private void beforeRun(final Task task) {
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            task.setNextExecuteNanoTimeokhttp(-1L);
            final TaskQueue queueokhttp = task.getQueueokhttp();
            checkNotNull(queueokhttp);
            queueokhttp.getFutureTasksokhttp().remove(task);
            readyQueues.remove(queueokhttp);
            queueokhttp.setActiveTaskokhttp(task);
            busyQueues.add(queueokhttp);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }
    public Task awaitTaskToRun() {
        boolean z;
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            while (!readyQueues.isEmpty()) {
                final long jNanoTime = backend.nanoTime();
                final Iterator<TaskQueue> it = readyQueues.iterator();
                long jMin = LocationRequestCompat.PASSIVE_INTERVAL;
                Task task = null;
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    final Task task2 = it.next().getFutureTasksokhttp().get(0);
                    final long jMax = Math.max(0L, task2.getNextExecuteNanoTime() - jNanoTime);
                    if (0 < jMax) {
                        jMin = Math.min(jMax, jMin);
                    } else {
                        if (null != task) {
                            z = true;
                            break;
                        }
                        task = task2;
                    }
                }
                if (null != task) {
                    this.beforeRun(task);
                    if (z || (!coordinatorWaiting && !readyQueues.isEmpty())) {
                        backend.execute(runnable);
                    }
                    return task;
                }
                if (coordinatorWaiting) {
                    if (jMin < coordinatorWakeUpAt - jNanoTime) {
                        backend.coordinatorNotify(this);
                    }
                    return null;
                }
                coordinatorWaiting = true;
                coordinatorWakeUpAt = jNanoTime + jMin;
                try {
                    backend.coordinatorWait(this, jMin);
                } finally {
                    coordinatorWaiting = false;
                }
            }
            return null;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }
    public void kickCoordinatorokhttp(final TaskQueue taskQueue) {
        Intrinsics.checkNotNullParameter(taskQueue, "taskQueue");
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            if (null == taskQueue.getActiveTaskokhttp()) {
                if (!taskQueue.getFutureTasksokhttp().isEmpty()) {
                    Util.addIfAbsent(readyQueues, taskQueue);
                } else {
                    readyQueues.remove(taskQueue);
                }
            }
            if (coordinatorWaiting) {
                backend.coordinatorNotify(this);
                return;
            } else {
                backend.execute(runnable);
                return;
            }
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }
    private void runTask(final Task task) {
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        final Thread threadCurrentThread = Thread.currentThread();
        final String name = threadCurrentThread.getName();
        threadCurrentThread.setName(task.getName());
        try {
            final long jRunOnce = task.runOnce();
            synchronized (this) {
                this.afterRun(task, jRunOnce);
                final Unit unit = Unit.INSTANCE;
            }
            threadCurrentThread.setName(name);
        } catch (final Throwable th) {
            synchronized (this) {
                this.afterRun(task, -1L);
                final Unit unit2 = Unit.INSTANCE;
                threadCurrentThread.setName(name);
                throw th;
            }
        }
    }
    public interface Backend {
        void beforeTask(TaskRunner taskRunner);

        void coordinatorNotify(TaskRunner taskRunner);

        void coordinatorWait(TaskRunner taskRunner, long j2);

        void execute(Runnable runnable);

        long nanoTime();
    }
    public static final class RealBackend implements Backend {
        private final ThreadPoolExecutor executor;

        public RealBackend(final ThreadFactory threadFactory) {
            Intrinsics.checkNotNullParameter(threadFactory, "threadFactory");
            executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory);
        }
        public void beforeTask(final TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        }
        public long nanoTime() {
            return System.nanoTime();
        }
        public void coordinatorWait(final TaskRunner taskRunner, final long j2) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            final long j3 = j2 / 1000000;
            final long j4 = j2 - (1000000 * j3);
            if (0 < j3 || 0 < j2) {
                try {
                    taskRunner.wait(j3, (int) j4);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        public void execute(final Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            executor.execute(runnable);
        }
        public void shutdown() {
            executor.shutdown();
        }
        public void coordinatorNotify(final TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            taskRunner.notify();
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Logger getLogger() {
            return logger;
        }
    }
}
