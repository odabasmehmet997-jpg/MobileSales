package org.simpleframework.xml.core;


class Caller {
    private final Function commit;
    private final Function complete;
    private final Context context;
    private final Function persist;
    private final Function replace;
    private final Function resolve;
    private final Function validate;

    public Caller(final Scanner scanner, final Context context) {
        validate = scanner.getValidate();
        complete = scanner.getComplete();
        replace = scanner.getReplace();
        resolve = scanner.getResolve();
        persist = scanner.getPersist();
        commit = scanner.getCommit();
        this.context = context;
    }

    public Object replace(final Object obj) throws Exception {
        final Function function = replace;
        return null != function ? function.call(context, obj) : obj;
    }

    public Object resolve(final Object obj) throws Exception {
        final Function function = resolve;
        return null != function ? function.call(context, obj) : obj;
    }

    public void commit(final Object obj) throws Exception {
        final Function function = commit;
        if (null != function) {
            function.call(context, obj);
        }
    }

    public void validate(final Object obj) throws Exception {
        final Function function = validate;
        if (null != function) {
            function.call(context, obj);
        }
    }

    public void persist(final Object obj) throws Exception {
        final Function function = persist;
        if (null != function) {
            function.call(context, obj);
        }
    }

    public void complete(final Object obj) throws Exception {
        final Function function = complete;
        if (null != function) {
            function.call(context, obj);
        }
    }
}
