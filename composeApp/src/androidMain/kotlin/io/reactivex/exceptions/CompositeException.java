package io.reactivex.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;



public final class CompositeException extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List<Throwable> exceptions;
    private final String message;

    public CompositeException(final Throwable... thArr) {
        this(null == thArr ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(thArr));
    }

    public CompositeException(final Iterable<? extends Throwable> iterable) {
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        final ArrayList arrayList = new ArrayList();
        if (null != iterable) {
            for (final Throwable th : iterable) {
                if (th instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) th).exceptions);
                } else if (null != th) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (linkedHashSet.isEmpty()) {
            throw new IllegalArgumentException("errors is empty");
        }
        arrayList.addAll(linkedHashSet);
        final List<Throwable> unmodifiableList = Collections.unmodifiableList(arrayList);
        exceptions = unmodifiableList;
        message = unmodifiableList.size() + " exceptions occurred. ";
    }

    public List<Throwable> getExceptions() {
        return exceptions;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return message;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        try {
            if (null == this.cause) {
                final CompositeExceptionCausalChain compositeExceptionCausalChain = new CompositeExceptionCausalChain();
                final HashSet hashSet = new HashSet();
                final Iterator<Throwable> it = exceptions.iterator();
                Throwable th = compositeExceptionCausalChain;
                while (it.hasNext()) {
                    Throwable next = it.next();
                    if (!hashSet.contains(next)) {
                        hashSet.add(next);
                        for (final Throwable th2 : this.getListOfCauses(next)) {
                            if (hashSet.contains(th2)) {
                                next = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                            } else {
                                hashSet.add(th2);
                            }
                        }
                        try {
                            th.initCause(next);
                        } catch (final Throwable unused) {
                        }
                        th = this.getRootCause(th);
                    }
                }
                cause = compositeExceptionCausalChain;
            }
        } catch (final Throwable th3) {
            throw th3;
        }
        return cause;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(final PrintStream printStream) {
        this.printStackTrace(new WrappedPrintStream(printStream));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(final PrintWriter printWriter) {
        this.printStackTrace(new WrappedPrintWriter(printWriter));
    }

    private void printStackTrace(final PrintStreamOrWriter printStreamOrWriter) {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(this);
        sb.append('\n');
        for (final StackTraceElement stackTraceElement : this.getStackTrace()) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        int i2 = 1;
        for (final Throwable th : exceptions) {
            sb.append("  ComposedException ");
            sb.append(i2);
            sb.append(" :\n");
            this.appendStackTrace(sb, th, "\t");
            i2++;
        }
        printStreamOrWriter.println(sb.toString());
    }

    private void appendStackTrace(final StringBuilder sb, final Throwable th, final String str) {
        sb.append(str);
        sb.append(th);
        sb.append('\n');
        for (final StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append("\t\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        if (null != th.getCause ()) {
            sb.append("\tCaused by: ");
            this.appendStackTrace(sb, th.getCause(), "");
        }
    }

    static abstract class PrintStreamOrWriter {
        abstract void println(Object obj);

        PrintStreamOrWriter() {
        }
    }

    static final class WrappedPrintStream extends PrintStreamOrWriter {
        private final PrintStream printStream;

        WrappedPrintStream(final PrintStream printStream) {
            this.printStream = printStream;
        }

        @Override // io.reactivex.exceptions.CompositeException.PrintStreamOrWriter
        void println(final Object obj) {
            printStream.println(obj);
        }
    }

    static final class WrappedPrintWriter extends PrintStreamOrWriter {
        private final PrintWriter printWriter;

        WrappedPrintWriter(final PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        @Override // io.reactivex.exceptions.CompositeException.PrintStreamOrWriter
        void println(final Object obj) {
            printWriter.println(obj);
        }
    }

    static final class CompositeExceptionCausalChain extends RuntimeException {
        static final String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
        private static final long serialVersionUID = 3875212506787802066L;

        CompositeExceptionCausalChain() {
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return CompositeExceptionCausalChain.MESSAGE;
        }
    }

    private List<Throwable> getListOfCauses(final Throwable th) {
        final ArrayList arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (null != cause && cause != th) {
            while (true) {
                arrayList.add(cause);
                final Throwable cause2 = cause.getCause();
                if (null == cause2 || cause2 == cause) {
                    break;
                }
                cause = cause2;
            }
        }
        return arrayList;
    }

    public int size() {
        return exceptions.size();
    }

    Throwable getRootCause(final Throwable th) {
        Throwable cause = th.getCause();
        if (null == cause || th == cause) {
            return th;
        }
        while (true) {
            final Throwable cause2 = cause.getCause();
            if (null == cause2 || cause2 == cause) {
                break;
            }
            cause = cause2;
        }
        return cause;
    }
}
