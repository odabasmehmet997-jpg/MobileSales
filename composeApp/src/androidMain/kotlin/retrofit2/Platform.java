package retrofit2;

import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

class Platform {
    private static final Platform PLATFORM = findPlatform();
    private final boolean hasJava8Types;
    private final Constructor<MethodHandles.Lookup> lookupConstructor;
    Executor defaultCallbackExecutor() {
        return null;
    }
    static Platform get() {
        return PLATFORM;
    }
    private static Platform findPlatform() {
        if ("Dalvik".equals(System.getProperty("java.vm.name"))) {
            return new Android();
        }
        return new Platform(true);
    }
    Platform(boolean z) {
        this.hasJava8Types = z;
        Constructor<MethodHandles.Lookup> declaredConstructor = null;
        if (z) {
            try {
                declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                declaredConstructor.setAccessible(true);
            } catch (NoClassDefFoundError | NoSuchMethodException unused) {
            }
        }
        this.lookupConstructor = declaredConstructor;
    }
    List<? extends CallAdapter.Factory> defaultCallAdapterFactories(Executor executor) {
        DefaultCallAdapterFactory defaultCallAdapterFactory = new DefaultCallAdapterFactory(executor);
        return this.hasJava8Types ? Arrays.asList(CompletableFutureCallAdapterFactory.INSTANCE, defaultCallAdapterFactory) : Collections.singletonList(defaultCallAdapterFactory);
    }
    int defaultCallAdapterFactoriesSize() {
        return this.hasJava8Types ? 2 : 1;
    }
    List<? extends Converter.Factory> defaultConverterFactories() {
        return this.hasJava8Types ? Collections.singletonList(OptionalConverterFactory.INSTANCE) : Collections.emptyList();
    }
    int defaultConverterFactoriesSize() {
        return this.hasJava8Types ? 1 : 0;
    }
    boolean isDefaultMethod(Method method) {
        return this.hasJava8Types && method.isDefault();
    }
    Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
        MethodHandles.Lookup lookup;
        Constructor<MethodHandles.Lookup> constructor = this.lookupConstructor;
        if (constructor != null) {
            lookup = constructor.newInstance(cls, -1);
        } else {
            lookup = MethodHandles.lookup();
        }
        return lookup.unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }
    static final class Android extends Platform {
        Android() {
            super(true);
        }
        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }
        Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
            return super.invokeDefaultMethod(method, cls, obj, objArr);
        }

        static final class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            MainThreadExecutor() {}
            public void execute(Runnable runnable) {
                this.handler.post(runnable);
            }
        }
    }
}
