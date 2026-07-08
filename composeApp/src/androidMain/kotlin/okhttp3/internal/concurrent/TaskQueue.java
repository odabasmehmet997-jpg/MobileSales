package okhttp3.internal.concurrent;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Http2Connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;

public final class TaskQueue {
    private final List<Task> futureTasks;
    private final String name;
    private final TaskRunner taskRunner;
    private Task activeTask;
    private boolean cancelActiveTask;
    private boolean shutdown;
    public TaskQueue(TaskRunner taskRunner, String name) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(name, "name");
        this.taskRunner = taskRunner;
        this.name = name;
        this.futureTasks = new ArrayList();
    }
    public static void scheduledefault(TaskQueue taskQueue, Task task, long j2, int i2, Object obj) {
        if (0 != (i2 & 2)) {
            j2 = 0;
        }
        taskQueue.schedule(task, j2);
    }
    public static void scheduledefault(TaskQueue taskQueue, String name, long j2, Function0 block, int i2, Object obj) {
        if (0 != (i2 & 2)) {
            j2 = 0;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        taskQueue.schedule(new C35872(name, block), j2);
    }
    public static void executedefault(TaskQueue taskQueue, String name, long j2, boolean z, Function0 block, int i2, Object obj) {
        if (0 != (i2 & 2)) {
            j2 = 0;
        }
        if (0 != (i2 & 4)) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        taskQueue.schedule(new C35861(name, z, block), j2);
    }
    public TaskRunner getTaskRunnerokhttp() {
        return this.taskRunner;
    }
    public String getNameokhttp() {
        return this.name;
    }
    public boolean getShutdownokhttp() {
        return this.shutdown;
    }
    public void setShutdownokhttp(boolean z) {
        this.shutdown = z;
    }
    public Task getActiveTaskokhttp() {
        return this.activeTask;
    }
    public void setActiveTaskokhttp(Task task) {
        this.activeTask = task;
    }
    public List<Task> getFutureTasksokhttp() {
        return this.futureTasks;
    }
    public boolean getCancelActiveTaskokhttp() {
        return this.cancelActiveTask;
    }
    public void setCancelActiveTaskokhttp(boolean z) {
        this.cancelActiveTask = z;
    }
    public List<Task> getScheduledTasks() {
        List<Task> list;
        synchronized (this.taskRunner) {
            list = CollectionsKt.toList(futureTasks);
        }
        return list;
    }
    public void schedule(Task task, long j2) {
        Intrinsics.checkNotNullParameter(task, "task");
        synchronized (this.taskRunner) {
            if (shutdown) {
                if (task.getCancelable()) {
                    if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                        TaskLogger.log(task, this, "schedule canceled (queue is shutdown)");
                    }
                    return;
                } else {
                    if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                        TaskLogger.log(task, this, "schedule failed (queue is shutdown)");
                    }
                    throw new RejectedExecutionException();
                }
            }
            if (scheduleAndDecideokhttp(task, j2, false)) {
                taskRunner.kickCoordinatorokhttp(this);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
    public void schedule(String name, long j2, Function0<Long> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        schedule(new C35872(name, block), j2);
    }
    public void execute(String name, long j2, boolean z, Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(block, "block");
        schedule(new C35861(name, z, block), j2);
    }
    public CountDownLatch idleLatch() {
        synchronized (this.taskRunner) {
            if (null == this.activeTask && futureTasks.isEmpty()) {
                return new CountDownLatch(0);
            }
            Task activeTaskokhttp = activeTask;
            if (activeTaskokhttp instanceof AwaitIdleTask) {
                return ((AwaitIdleTask) activeTaskokhttp).getLatch();
            }
            for (Task task : futureTasks) {
                if (task instanceof AwaitIdleTask) {
                    return ((AwaitIdleTask) task).getLatch();
                }
            }
            AwaitIdleTask awaitIdleTask = new AwaitIdleTask();
            if (scheduleAndDecideokhttp(awaitIdleTask, 0L, false)) {
                taskRunner.kickCoordinatorokhttp(this);
            }
            return awaitIdleTask.getLatch();
        }
    }
    public boolean scheduleAndDecideokhttp(Task task, long j2, boolean z) {
        String strStringPlus;
        Intrinsics.checkNotNullParameter(task, "task");
        task.initQueueokhttp(this);
        long jNanoTime = this.taskRunner.getBackend().nanoTime();
        long j3 = jNanoTime + j2;
        int iIndexOf = this.futureTasks.indexOf(task);
        if (-1 != iIndexOf) {
            if (task.getNextExecuteNanoTime() <= j3) {
                if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                    TaskLogger.log(task, this, "already scheduled");
                }
                return false;
            }
            this.futureTasks.remove(iIndexOf);
        }
        task.setNextExecuteNanoTimeokhttp(j3);
        if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
            if (z) {
                strStringPlus = Intrinsics.stringPlus("run again after ", TaskLogger.formatDuration(j3 - jNanoTime));
            } else {
                strStringPlus = Intrinsics.stringPlus("scheduled after ", TaskLogger.formatDuration(j3 - jNanoTime));
            }
            TaskLogger.log(task, this, strStringPlus);
        }
        Iterator<Task> it = this.futureTasks.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                size = -1;
                break;
            }
            if (it.next().getNextExecuteNanoTime() - jNanoTime > j2) {
                break;
            }
            size++;
        }
        if (-1 == size) {
            size = this.futureTasks.size();
        }
        this.futureTasks.add(size, task);
        return 0 == size;
    }
    public boolean cancelAllAndDecideokhttp() {
        Task task = this.activeTask;
        if (null != task) {
            Intrinsics.checkNotNull(task);
            if (task.getCancelable()) {
                this.cancelActiveTask = true;
            }
        }
        int size = this.futureTasks.size() - 1;
        boolean z = false;
        if (0 <= size) {
            while (true) {
                int i2 = size - 1;
                if (this.futureTasks.get(size).getCancelable()) {
                    Task task2 = this.futureTasks.get(size);
                    if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
                        TaskLogger.log(task2, this, "canceled");
                    }
                    this.futureTasks.remove(size);
                    z = true;
                }
                if (0 > i2) {
                    break;
                }
                size = i2;
            }
        }
        return z;
    }
    public String toString() {
        return this.name;
    }
    public void cancelAll() {
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this.taskRunner) {
                try {
                    if (cancelAllAndDecideokhttp()) {
                        taskRunner.kickCoordinatorokhttp(this);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }
    public void shutdown() {
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            synchronized (this.taskRunner) {
                try {
                    shutdown = true;
                    if (cancelAllAndDecideokhttp()) {
                        taskRunner.kickCoordinatorokhttp(this);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }
    public static final class C35872 extends Task {
        final Function0<Long> block;
        final String name;
        public C35872(String str, Function0<Long> function0) {
            super(str, false, 2, null);
            this.name = str;
            this.block = function0;
        }
        public long runOnce() {
            return this.block.invoke().longValue();
        }
    }
    public static final class C35861 extends Task {
        final Function0<Unit> block;
        final boolean cancelable;
        final String name;
        public C35861(String str, boolean z, Function0<Unit> function0) {
            super(str, z, this);
            this.name = str;
            this.cancelable = z;
            this.block = function0;
        }
        public long runOnce() {
            this.block.invoke();
            return -1L;
        }
    }
    private static final class AwaitIdleTask extends Task {

      private final CountDownLatch latch;

        public AwaitIdleTask() {
            super(Intrinsics.stringPlus(Util.okHttpName, " awaitIdle"), z, this);
            this.latch = new CountDownLatch(1);
        }

        public CountDownLatch getLatch() {
            return this.latch;
        }

        public long runOnce() {
            this.latch.countDown();
            return -1L;
        }
    }
}
