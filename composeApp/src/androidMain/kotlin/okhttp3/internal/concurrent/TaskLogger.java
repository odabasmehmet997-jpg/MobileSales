package okhttp3.internal.concurrent;

import com.proje.mobilesales.core.utils.StringUtils;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2Connection;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public class TaskLogger {
    public static StringUtils PrimitiveCompanionObjects;
    public static void taskLog(Task task, TaskQueue queue, Function0<String> messageBlock) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(messageBlock, "messageBlock");
        if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
            log(task, queue, messageBlock.invoke());
        }
    }
    public static <T> T logElapsed(Task task, TaskQueue queue, Function0<? extends T> block) {
        long jNanoTime;
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(block, "block");
        boolean zIsLoggable = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
        if (zIsLoggable) {
            jNanoTime = queue.getTaskRunnerokhttp().getBackend().nanoTime();
            log(task, queue, "starting");
        } else {
            jNanoTime = -1;
        }
        try {
            T tInvoke = block.invoke();
            InlineMarker.finallyStart(1);
            if (zIsLoggable) {
                log(task, queue, Intrinsics.stringPlus("finished run in ", formatDuration(queue.getTaskRunnerokhttp().getBackend().nanoTime() - jNanoTime)));
            }
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (zIsLoggable) {
                log(task, queue, Intrinsics.stringPlus("failed a run in ", formatDuration(queue.getTaskRunnerokhttp().getBackend().nanoTime() - jNanoTime)));
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }
    static void log(Task task, TaskQueue taskQueue, String str) {
        Logger logger = TaskRunner.Companion.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append(taskQueue.getNameokhttp());
        sb.append(' ');
        StringUtils primitiveCompanionObjects = StringUtils.INSTANCE;
        String str2 = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        sb.append(str2);
        sb.append(": ");
        sb.append(task.getName());
        logger.fine(sb.toString());
    }
    public static String formatDuration(long j2) {
        String str;
        if (-999500000 >= j2) {
            str = ((j2 - 500000000) / Http2Connection.DEGRADED_PONG_TIMEOUT_NS) + " s ";
        } else if (-999500 >= j2) {
            str = ((j2 - 500000) / 1000000) + " ms";
        } else if (0 >= j2) {
            str = ((j2 - 500) / 1000) + " \u00b5s";
        } else if (999500 > j2) {
            str = ((j2 + 500) / 1000) + " \u00b5s";
        } else if (999500000 > j2) {
            str = ((j2 + 500000) / 1000000) + " ms";
        } else {
            str = ((j2 + 500000000) / Http2Connection.DEGRADED_PONG_TIMEOUT_NS) + " s ";
        }
        StringUtils primitiveCompanionObjects = StringUtils.INSTANCE;
        final String str2 = String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
        checkNotNullExpressionValue(str2, "format(format, *args)");
        return str2;
    }
}
