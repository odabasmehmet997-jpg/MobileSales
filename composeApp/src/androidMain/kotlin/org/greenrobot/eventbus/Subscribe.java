package org.greenrobot.eventbus;


public @interface Subscribe {
    int priority() default 0;
    boolean sticky() default false;
    ThreadMode threadMode() default ThreadMode.POSTING;
}
