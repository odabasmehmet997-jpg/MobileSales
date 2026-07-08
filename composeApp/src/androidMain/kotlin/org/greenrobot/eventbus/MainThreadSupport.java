package org.greenrobot.eventbus;

import android.os.Looper;

public interface MainThreadSupport {
    Poster createPoster(EventBus eventBus);
    boolean isMainThread();
    class AndroidHandlerMainThreadSupport implements MainThreadSupport {
        private final Looper looper;
        public AndroidHandlerMainThreadSupport(final Looper looper) {
            this.looper = looper;
        }
        public boolean isMainThread() {
            return looper == Looper.myLooper();
        }
        public Poster createPoster(final EventBus eventBus) {
            return new HandlerPoster(eventBus, looper, 10);
        }
    }
}
